package uz.pdp.lcsystem.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
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
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Positive(message = "groupId musbat bulishi kerak!")
    @NotNull(message = "groupId bush bulishi kerak emas")
    private Long groupId;

    @NotNull(message = "studentId bush bulishi mumkun emas!")
    @Positive(message = "studentId musbat bulishi kerak")
    private Long studentId;
}
