package uz.pdp.lcsystem.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.lcsystem.entity.Attachment;
import uz.pdp.lcsystem.enums.Gender;
import uz.pdp.lcsystem.enums.RoleEnum;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeDTO {

    private long id;

    private String firstName;

    private String lastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    private String phoneNumber;

    private Gender gender;


    private Long salary;




    private UserDTO user;

    private Long attachmentId;

}
