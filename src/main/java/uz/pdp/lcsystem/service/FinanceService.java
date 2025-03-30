package uz.pdp.lcsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.lcsystem.entity.Finance;
import uz.pdp.lcsystem.exception.RestException;
import uz.pdp.lcsystem.payload.FinanceDTO;
import uz.pdp.lcsystem.payload.withoutId.FinanceDto;
import uz.pdp.lcsystem.repository.FinanceRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FinanceService {
    private final FinanceRepository financeRepository;


    public List<FinanceDTO> findAll() {
        List<Finance> all = financeRepository.findAll();

        List<FinanceDTO> resultList = new ArrayList<>();

        if (all.isEmpty()) {
            throw RestException.error("finance list is empty");
        }

        for (Finance finance : all) {
            FinanceDTO build = FinanceDTO.builder()
                    .id(finance.getId())
                    .category(finance.getCategory())
                    .receiver(finance.getReceiver())
                    .amount(finance.getAmount())
                    .name(finance.getName())
                    .paid(finance.isPaid())
                    .date(finance.getDate())
                    .build();
            resultList.add(build);
        }
        return resultList;
    }


    public FinanceDTO save( FinanceDto financeDTO) {


        Finance build = Finance.builder()
                .amount(financeDTO.getAmount())
                .category(financeDTO.getCategory())
                .date(financeDTO.getDate())
                .name(financeDTO.getName())
                .paid(financeDTO.isPaid())
                .receiver(financeDTO.getReceiver())
                .build();
        Finance save = financeRepository.save(build);
        FinanceDTO build1 = FinanceDTO.builder()
                .amount(save.getAmount())
                .category(save.getCategory())
                .date(save.getDate())
                .name(save.getName())
                .paid(save.isPaid())
                .receiver(save.getReceiver())
                .build();
        return build1;

    }

    public List<FinanceDTO> filtrate(LocalDate from, LocalDate to) {
        List<Finance> all = financeRepository.findAll();
        List<FinanceDTO> resultList = new ArrayList<>();

        for (Finance finance : all) {
            if (finance.getCreated().toLocalDateTime().toLocalDate().isAfter(from)
                    && finance.getCreated().toLocalDateTime().toLocalDate().isBefore(to)
            ) {
                FinanceDTO build = FinanceDTO.builder()
                        .id(finance.getId())
                        .category(finance.getCategory())
                        .receiver(finance.getReceiver())
                        .amount(finance.getAmount())
                        .name(finance.getName())
                        .paid(finance.isPaid())
                        .date(finance.getDate())

                        .build();
                resultList.add(build);
            }

        }
        if (resultList.isEmpty()) {
            throw RestException.error("finance list is empty");
        }
        return resultList;
    }







}
