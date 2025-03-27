package uz.pdp.lcsystem.payload;

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

    private String name;

    private LocalDate date;

    private String category;

    private String receiver;

    private Double amount;

    private boolean paid;

}