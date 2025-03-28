package uz.pdp.lcsystem.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uz.pdp.lcsystem.entity.Payment;
import uz.pdp.lcsystem.repository.PaymentRepository;

import java.util.List;
import java.util.concurrent.TimeUnit;

@EnableScheduling
@Component
@RequiredArgsConstructor
public class PaymentCreator {
    private final PaymentRepository paymentRepository;

    @Scheduled(fixedRate = 30 , timeUnit = TimeUnit.DAYS)
    public void createPayment() {
        List<Payment> all = paymentRepository.findAll();

        for (Payment payment : all) {
            if(payment.isPaid()) {
                Payment build = Payment.builder()
                        .paid(false)
                        .group(payment.getGroup())
                        .student(payment.getStudent())
                        .paymentDate(payment.getPaymentDate().plusDays(30))
                        .amount(payment.getAmount())
                        .build();
                paymentRepository.save(build);
            }
        }
    }
}
