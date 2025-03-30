package uz.pdp.lcsystem.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.lcsystem.entity.User;
import uz.pdp.lcsystem.payload.LoginDTO;
import uz.pdp.lcsystem.repository.UserRepository;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Lazy
    private final JwtProvider jwtProvider;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return   userRepository.getUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public String login(LoginDTO loginDTO) {
        User user = (User) userRepository.getUserByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(loginDTO.getUsername()));
        boolean matches = passwordEncoder.matches(loginDTO.getPassword(), user.getPassword());

        if (!matches)
            throw new AccessDeniedException("Invalid username or password");

        return jwtProvider.generateToken(user.getUsername(), new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000));

    }



}
