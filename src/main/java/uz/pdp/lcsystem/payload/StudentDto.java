package uz.pdp.lcsystem.payload;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import uz.pdp.lcsystem.entity.Student;
import uz.pdp.lcsystem.enums.Gender;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Student}
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StudentDto implements Serializable {
    Long id;
    String firstName;
    String lastName;
    Gender gender;

    private Integer userId;

    List<StudentAttendanceDTO> attendances;
}