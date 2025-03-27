package uz.pdp.lcsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import uz.pdp.lcsystem.entity.attendences.StudentAttendance;
import uz.pdp.lcsystem.entity.tempAbs.AbsLongEntity;
import uz.pdp.lcsystem.enums.Gender;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@SQLDelete(sql = "UPDATE student SET deleted = true WHERE id = ?")
public class Student extends AbsLongEntity {

    private String firstName;

    private String lastName;

    private String phoneNumber;


    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private User user;





}
