package uz.pdp.lcsystem.payload;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @NotNull(message = "amount bush bulishi mumkun emas!")
    @Positive(message = "amount musbat bulishi kerak")
    private Double amount;

    private boolean paid;

    @NotNull(message = "date bush bulishi mumkun emas!")
    @FutureOrPresent(message = "to'lov sanasi bugun yoki kelajak bulishi kerak")
    private LocalDate date;

    @Positive(message = "groupId musbat bulishi kerak!")
    @NotNull(message = "groupId bush bulishi kerak emas")
    private Long groupId;

    @NotNull(message = "studentId bush bulishi mumkun emas!")
    @Positive(message = "studentId musbat bulishi kerak")
    private Long studentId;
}
