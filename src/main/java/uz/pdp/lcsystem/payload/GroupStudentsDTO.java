package uz.pdp.lcsystem.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @NotNull(message = "groupId bush bulishi kerak emas!")
    @Positive(message = "groupId musbat bulishi kerak!")
    private Long groupId;

    @NotBlank(message = "groupName bush bulishi mumkun emas")
    private String groupName;

    @NotNull(message = "studentId bush bulishi mumkun emas")
    @Positive(message = "studentId musbat bulishi kerak")
    private Long studentId;

    @NotBlank(message = "studentName bush bulishi mumkun emas")
    private String studentName;

}
