package uz.pdp.lcsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lcsystem.payload.ApiResult;
import uz.pdp.lcsystem.payload.FinanceDTO;
import uz.pdp.lcsystem.payload.PaymentDTO;
import uz.pdp.lcsystem.payload.withoutId.FinanceDto;
import uz.pdp.lcsystem.payload.withoutId.PaymentDto;
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

    @Secured({"ADMIN","MANAGER"})
    @GetMapping
    public ApiResult<List<FinanceDTO>> getAll(){

        List<FinanceDTO> result = financeService.findAll();

        return ApiResult.success(result);
    }

    @Secured({"ADMIN","MANAGER"})
    @PostMapping("/create")
    @Transactional
    public ApiResult<FinanceDTO> create(@RequestBody FinanceDto financeDTO){
        FinanceDTO save = financeService.save(financeDTO);
        return ApiResult.success(save);
    }

    @Secured({"ADMIN","MANAGER"})
    @GetMapping("/filter")
    public ApiResult<List<FinanceDTO>> filter(@RequestBody LocalDate from
            , @RequestBody LocalDate to){
        List<FinanceDTO> filtrate = financeService.filtrate(from, to);
        return ApiResult.success(filtrate);
    }
    @Secured({"ADMIN","MANAGER"})
    @GetMapping("/payment")
    public ApiResult<List<PaymentDTO>> getPayment(){
        List<PaymentDTO> payments = paymentService.getPayments();
        return ApiResult.success(payments);
    }

    @Secured({"ADMIN","MANAGER"})
    @GetMapping("/payment/debtors")
    public ApiResult<List<PaymentDTO>> getPaymentDebtors(){
        List<PaymentDTO> debtors = paymentService.debtors();
        return ApiResult.success(debtors);
    }

    @Secured({"ADMIN","MANAGER"})
    @PutMapping("/payment/update")
    public ApiResult<PaymentDTO> update(@RequestBody LocalDate date , @RequestBody Long studentId ){
        PaymentDTO byStudentId = paymentService.getByStudentId(studentId, date);
        return ApiResult.success(byStudentId);
    }

    @Secured({"ADMIN","MANAGER"})
    @PostMapping("/payment/create")
    public ApiResult<PaymentDTO> create(@RequestBody PaymentDto paymentDTO){
        PaymentDTO paymentDTO1 = paymentService.create(paymentDTO);
        return ApiResult.success(paymentDTO1);
    }
}
