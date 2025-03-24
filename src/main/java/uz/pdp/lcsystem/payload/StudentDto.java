package uz.pdp.lcsystem.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import uz.pdp.lcsystem.entity.Student;
import uz.pdp.lcsystem.enums.Gender;
import uz.pdp.lcsystem.enums.RoleEnum;

import java.io.Serializable;
import java.util.ArrayList;
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

    private String username;

    @JsonIgnore
    private String password;
    private String email;
    private RoleEnum role;
//    private Integer userId;

    List<StudentAttendanceDTO> attendances;



}