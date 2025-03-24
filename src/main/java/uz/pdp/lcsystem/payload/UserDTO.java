package uz.pdp.lcsystem.payload;

import lombok.*;

import uz.pdp.lcsystem.enums.RoleEnum;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO implements Serializable {
    private Long id;
    private String username;
    private String password;
    private String email;
    private RoleEnum role;
}