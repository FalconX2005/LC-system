package uz.pdp.lcsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lcsystem.entity.PasswordResetToken;
import uz.pdp.lcsystem.entity.User;
;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);
    Optional<PasswordResetToken> findByUser(User user);
}