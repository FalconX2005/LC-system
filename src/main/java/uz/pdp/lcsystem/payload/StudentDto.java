package uz.pdp.lcsystem.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.lcsystem.entity.Student;
import uz.pdp.lcsystem.enums.Gender;
import uz.pdp.lcsystem.enums.RoleEnum;

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

    private Long id;


    private String firstName;
    private String lastName;
    private Gender gender;
    private String phoneNumber;
    private String username;

    private String password;
    private String email;
    private RoleEnum role;



//    private Long userId;

//    private List<StudentAttendanceDTO> attendances;
}