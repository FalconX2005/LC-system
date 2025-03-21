package uz.pdp.lcsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.springframework.stereotype.Component;
import uz.pdp.lcsystem.entity.tempAbs.AbsLongEntity;
import uz.pdp.lcsystem.enums.RoleEnum;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id = ?")
public class User extends AbsLongEntity {

    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum;
}
