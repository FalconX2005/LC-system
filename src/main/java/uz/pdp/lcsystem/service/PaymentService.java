package uz.pdp.lcsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.lcsystem.entity.Group;
import uz.pdp.lcsystem.entity.Payment;
import uz.pdp.lcsystem.entity.Student;
import uz.pdp.lcsystem.exception.RestException;
import uz.pdp.lcsystem.payload.PaymentDTO;
import uz.pdp.lcsystem.payload.withoutId.PaymentDto;
import uz.pdp.lcsystem.repository.GroupRepository;
import uz.pdp.lcsystem.repository.GroupStudentsRepository;
import uz.pdp.lcsystem.repository.PaymentRepository;
import uz.pdp.lcsystem.repository.StudentRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    public List<PaymentDTO> getPayments() {
        List<Payment> payments = paymentRepository.findAll();
        List<PaymentDTO> resultList = new ArrayList<>();
        for (Payment payment : payments) {
            PaymentDTO build = PaymentDTO.builder()
                    .amount(payment.getAmount())
                    .date(payment.getPaymentDate())
                    .groupId(payment.getGroup().getId())
                    .studentId(payment.getStudent().getId())
                    .id(payment.getId())
                    .paid(payment.isPaid())
                    .build();
            resultList.add(build);
        }
        return resultList;
    }
    public List<PaymentDTO> debtors() {
        List<Payment> payments = paymentRepository.findAll();
        List<PaymentDTO> resultList = new ArrayList<>();
        for (Payment payment : payments) {
            if (!payment.isPaid()) {
                PaymentDTO build = PaymentDTO.builder()
                        .amount(payment.getAmount())
                        .date(payment.getPaymentDate())
                        .groupId(payment.getGroup().getId())
                        .studentId(payment.getStudent().getId())
                        .id(payment.getId())
                        .paid(payment.isPaid())
                        .build();
                resultList.add(build);
            }
        }
        return resultList;
    }

    //student ni id si va keyingi tolov qilishi kerak bolgan sanasidan foydalanib tekshirib agar tolov qilmagan bosa tolov qilish
    // qilgan bolsa ozini qaytarish uchun yozilgan
//    public PaymentDTO getByStudentId(Long studentId) {
////        Payment paymentDate1 = paymentRepository
////                .findByStudentIdAndPaymentDate(studentId, paymentDate);
//
//        if (paymentDate1.isPaid()){
//
//            throw RestException.error("Student already pay");
//        }
//
//        paymentDate1.setPaid(true);
//
//         paymentRepository.save(paymentDate1);
//
//        PaymentDTO result = PaymentDTO.builder()
//                .paid(paymentDate1.isPaid())
//                .amount(paymentDate1.getAmount())
//                .date(paymentDate1.getPaymentDate())
//                .groupId(paymentDate1.getGroup().getId())
//                .studentId(studentId)
//                .id(paymentDate1.getId())
//                .build();
//        return result;
    public PaymentDTO update(Long id,Long sum){
        Payment payment = paymentRepository.findById(id).get();
        System.out.println(payment.getPaymentDate());

        if (sum > payment.getAmount() || payment.getAmount()-sum < 0) {
            payment.setPaid(true);
            throw RestException.error("student already paid");
        }
        payment.setAmount(payment.getAmount()-sum);
        payment.setPaymentDate((LocalDate) payment.getPaymentDate());
        payment.setPaid(false);
        payment.setStudent(payment.getStudent());
        payment.setGroup(payment.getGroup());
        Payment save = paymentRepository.save(payment);

        PaymentDTO build = PaymentDTO.builder()
                .amount(save.getAmount())
                .date((LocalDate) save.getPaymentDate())
                .groupId(save.getGroup().getId())
                .studentId(save.getStudent().getId())
                .id(save.getId())
                .paid(save.isPaid())
                .build();

        /*PaymentDTO build = PaymentDTO.builder()
                .amount(save.getAmount())
                .date(save.getPaymentDate())
                .groupId(save.getGroup() != null ? save.getGroup().getId() : null)
                .studentId(save.getStudent() != null ? save.getStudent().getId() : null)
                .id(save.getId())
                .paid(save.isPaid())
                .build();*/


        return  build;

    }

//    }
    public PaymentDTO create(PaymentDto paymentDTO) {

        PaymentDTO result = new PaymentDTO();

        Payment build = Payment.builder()
                .amount(paymentDTO.getAmount())
                .paymentDate(paymentDTO.getDate())
                .paid(paymentDTO.isPaid())
                .build();

        Optional<Group> byId = groupRepository.findById(paymentDTO.getGroupId());
        if (byId.isPresent()) {
            Group group = byId.get();
            build.setGroup(group);
        }
        else {
            throw RestException.error("Group not found");
        }
        Optional<Student> byId1 = studentRepository.findById(paymentDTO.getStudentId());
        if (byId1.isPresent()) {
            Student student = byId1.get();
            build.setStudent(student);

        }
        else {
            throw RestException.error("Student not found");
        }
        Payment save = paymentRepository.save(build);
        result.setId(save.getId());
        result.setAmount(save.getAmount());
        result.setDate(save.getPaymentDate());
        result.setGroupId(save.getGroup().getId());
        result.setStudentId(save.getStudent().getId());
        return result;
    }
}
