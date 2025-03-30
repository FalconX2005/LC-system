package uz.pdp.lcsystem.payload.withoutId;

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
public class GroupStudentsDto {


    @NotNull(message = "groupId bush bulishi kerak emas!")
    @Positive(message = "groupId musbat bulishi kerak!")
    private Long groupId;


    @NotNull(message = "studentId bush bulishi mumkun emas")
    @Positive(message = "studentId musbat bulishi kerak")
    private Long studentId;

}
