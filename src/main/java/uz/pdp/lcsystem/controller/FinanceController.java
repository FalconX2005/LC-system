package uz.pdp.lcsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lcsystem.payload.ApiResult;
import uz.pdp.lcsystem.payload.FinanceDTO;
import uz.pdp.lcsystem.payload.PaymentDTO;
import uz.pdp.lcsystem.service.FinanceService;
import uz.pdp.lcsystem.service.PaymentService;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/finance")
@RequiredArgsConstructor
@RestController
public class FinanceController {

    private final PaymentService paymentService;
    private final FinanceService financeService;

    @GetMapping
    public ApiResult<List<FinanceDTO>> getAll(){

        List<FinanceDTO> result = financeService.findAll();

        return ApiResult.success(result);
    }

    @PostMapping("/create")
    @Transactional
    public ApiResult<FinanceDTO> create(@RequestBody FinanceDTO financeDTO){
        FinanceDTO save = financeService.save(financeDTO);
        return ApiResult.success(save);
    }


    @GetMapping("/filter")
    public ApiResult<List<FinanceDTO>> filter(@RequestBody LocalDate from
            , @RequestBody LocalDate to){
        List<FinanceDTO> filtrate = financeService.filtrate(from, to);
        return ApiResult.success(filtrate);
    }

    @GetMapping("/payment")
    public ApiResult<List<PaymentDTO>> getPayment(){
        List<PaymentDTO> payments = paymentService.getPayments();
        return ApiResult.success(payments);
    }
    @GetMapping("/payment/debtors")
    public ApiResult<List<PaymentDTO>> getPaymentDebtors(){
        List<PaymentDTO> debtors = paymentService.debtors();
        return ApiResult.success(debtors);
    }

    @PutMapping("/payment/update")
    public ApiResult<PaymentDTO> update(@RequestBody LocalDate date , @RequestBody Long studentId ){
        PaymentDTO byStudentId = paymentService.getByStudentId(studentId, date);
        return ApiResult.success(byStudentId);
    }

    @PostMapping("/payment/create")
    public ApiResult<PaymentDTO> create(@RequestBody PaymentDTO paymentDTO){
        PaymentDTO paymentDTO1 = paymentService.create(paymentDTO);
        return ApiResult.success(paymentDTO1);
    }
}
