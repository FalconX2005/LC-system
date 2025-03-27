package uz.pdp.lcsystem.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PaymentDTO {

    private Long id;

    private Double amount;

    private boolean paid;

    private LocalDate date;

    private Long groupId;

    private Long studentId;
}
