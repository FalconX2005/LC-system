package uz.pdp.lcsystem.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResetPasswordTokenDTO {
    private String token;
    private String newPassword;
}
