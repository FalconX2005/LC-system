package uz.pdp.lcsystem.payload.withoutId;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by: Umar
 * DateTime: 3/26/2025 1:25 PM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeGroupDto {


    @NotNull(message = "groupId bush bulishi mumkun emas!")
    private Long groupId;



    @NotNull(message = "employeeId bush bulishi mumkun emas")
    private Long employeeId;


}
