package uz.pdp.lcsystem.payload.withoutId;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
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
public class StudentDto implements Serializable {



    @NotBlank(message = "firstName bush bulishi mumkun emas!")
    private String firstName;

    @NotBlank(message = "lastName bush bulishi mumkun emas!")
    private String lastName;

    @NotNull(message = "gender bush bulishi mumkun emas!")
    private Gender gender;

    @NotBlank(message = "phoneNumber bush bulishi kerak  emas!")
    @Pattern(regexp = "\\+998[0-9]{9}", message = "Telefon raqam +998XXXXXXXXX formatida bo‘lishi kerak")
    private String phoneNumber;

    @NotBlank(message = "userName bush bulishi mumkun emas!")
    private String username;

    @NotBlank(message = "password bush bulishi mumkun emas")
    @Size(min = 4,message = "Parol kamida 4ta belgidan iborat bulsin")
    private String password;

    @Email(message = "Email notug'ri formatda!")
    @NotBlank(message = "email bush bulishi mumkun emas!")
    private String email;

    @JsonIgnore
    private RoleEnum role=RoleEnum.STUDENT;



}