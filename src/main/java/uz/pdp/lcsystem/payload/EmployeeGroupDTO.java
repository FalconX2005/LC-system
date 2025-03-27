package uz.pdp.lcsystem.payload;

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

    private Long groupId;
    private String groupName;

    private Long employeeId;
    private String employeeName;
}
