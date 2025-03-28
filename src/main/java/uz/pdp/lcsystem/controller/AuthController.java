package uz.pdp.lcsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lcsystem.payload.LoginDTO;
import uz.pdp.lcsystem.payload.ResetPasswordTokenDTO;
import uz.pdp.lcsystem.security.AuthService;
import uz.pdp.lcsystem.security.ResetPasswordService;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final ResetPasswordService resetPasswordService;

    @PostMapping("/login")
    public Object login(@RequestBody LoginDTO loginDTO) {
        return authService.login(loginDTO);
    }




    @PostMapping("/request-reset")
    public void requestPasswordReset(@RequestParam String email) {
        resetPasswordService.requestPasswordReset(email);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String token,  @RequestBody ResetPasswordTokenDTO tokenDTO) {
        try {
            resetPasswordService.resetPassword(tokenDTO.getToken(), tokenDTO.getNewPassword());
            return ResponseEntity.ok("Password reset successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }




}
