package uz.pdp.lcsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import uz.pdp.lcsystem.entity.attendences.TeacherAttendance;
import uz.pdp.lcsystem.entity.tempAbs.AbsLongEntity;
import uz.pdp.lcsystem.enums.Gender;
import uz.pdp.lcsystem.enums.RoleEnum;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@SQLDelete(sql = "UPDATE employee SET deleted = true WHERE id = ?")
public class Employee  extends AbsLongEntity {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;


    @Column(nullable = false)
    private LocalDate birthDate;


    private String phoneNumber;


    @Enumerated(EnumType.STRING)
    private Gender gender;


    private Long salary;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    private User user;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "attachment_id")
    private Attachment attachment;

    @PreRemove
    private void preRemove() {
        if (user != null) {
            user.setDeleted(true);
        }
    }
}
