package uz.pdp.lcsystem.payload;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import uz.pdp.lcsystem.entity.Student;
import uz.pdp.lcsystem.enums.Gender;
import uz.pdp.lcsystem.enums.RoleEnum;

import java.io.Serializable;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StudentDTO implements Serializable {

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

    private List<StudentAttendanceDTO> attendances;
}