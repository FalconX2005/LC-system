package uz.pdp.lcsystem.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import uz.pdp.lcsystem.entity.User;
import uz.pdp.lcsystem.enums.RoleEnum;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO implements Serializable {

    Long id;

    @NotBlank(message = "userName bush bulishi mumkun emas!")
    String username;

    @NotBlank(message = "passeord bush bulishi mumkun emas!")
    @Size(min = 4,message = "password kamida 4ta belgi bulishi kerak")
    String password;

    @NotBlank(message = "email bush bulishi mumkun emas!")
    @Email(message = "Email format xato!")
    String email;

    RoleEnum roleEnum;
}