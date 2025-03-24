package uz.pdp.lcsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.lcsystem.entity.tempAbs.AbsLongEntity;
import uz.pdp.lcsystem.enums.RoleEnum;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity(name = "users")
public class User extends AbsLongEntity {

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String phoneNumber;

    private String email;

    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum;

    @Column(columnDefinition = "tsvector")
    private String searchVector;

    @PrePersist
    @PreUpdate
    public void updateSearchVector() {
        this.searchVector = firstName + " " + lastName;
    }

    public String getPhone() {
        return phoneNumber;
    }
}
