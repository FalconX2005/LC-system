package uz.pdp.lcsystem.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO for {@link uz.pdp.lcsystem.entity.Finance}
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FinanceDTO {

    private Long id;

    @NotBlank(message = "name bush bulishi mumkun emas!")
    private String name;

    @NotNull(message = "date bush bulishi mumkun emas!")
    private LocalDate date;

    @NotBlank(message = "category bush bulishi mumkun emas")
    private String category;

    @NotBlank(message = "reseiver bush bulishi mumkun emas")
    private String receiver;

    @Positive(message = "amount musbat bulishi kerak")
    @NotNull(message = "amount bush bulishi mumkun emas")
    private Double amount;

    private boolean paid;

}