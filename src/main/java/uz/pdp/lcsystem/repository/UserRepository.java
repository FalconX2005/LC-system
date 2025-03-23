package uz.pdp.lcsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lcsystem.entity.User;


import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserByUsername(String username);

    List<User> findByUsername(String username);

    Optional<User> findUserByEmail(String email);


}

