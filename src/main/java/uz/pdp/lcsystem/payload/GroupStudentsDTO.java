package uz.pdp.lcsystem.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GroupStudentsDTO {

    private Long id;

    private Long groupId;

    private Long studentId;

}
