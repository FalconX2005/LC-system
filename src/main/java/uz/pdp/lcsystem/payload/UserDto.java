package uz.pdp.lcsystem.payload;

import lombok.Value;
import uz.pdp.lcsystem.entity.User;
import uz.pdp.lcsystem.enums.RoleEnum;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
@Value
public class UserDto implements Serializable {
    Long id;
    String username;
    String password;
    String email;
    RoleEnum roleEnum;
}