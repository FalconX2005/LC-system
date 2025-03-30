package uz.pdp.lcsystem.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    @Value("${reset-password}")
    private String resetPasswordUrl;

    private final JavaMailSender mailSender;

    public void sendResetPasswordEmail(String email, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setFrom("email@gmail.com");
        message.setSubject("Reset Password");
        message.setText("Parolni bu token orqali o'zgartirish mumkin: \n" +
                "Tokendan nusxa olish:   " + token);
        mailSender.send(message);
    }
}
