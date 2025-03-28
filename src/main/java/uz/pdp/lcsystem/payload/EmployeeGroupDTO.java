package uz.pdp.lcsystem.payload;

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
public class EmployeeGroupDTO {

    private Long id;

    @NotNull(message = "groupId bush bulishi mumkun emas!")
    private Long groupId;

    @NotBlank(message = "groupName bush bulishi mumkun emas!!")
    private String groupName;

    @NotNull(message = "employeeId bush bulishi mumkun emas")
    private Long employeeId;

    @NotBlank(message = "emloyeeName bush bulishi mumkun emas")
    private String employeeName;
}
