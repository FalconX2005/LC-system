package uz.pdp.lcsystem.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import uz.pdp.lcsystem.payload.LoginDTO;


@Service
public interface AuthService extends UserDetailsService {


    Object login(LoginDTO loginDTO);

}
