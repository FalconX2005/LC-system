package uz.pdp.lcsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;
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
public class Student extends AbsLongEntity {

    private String firstName;

    private String lastName;

    private Gender gender;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "student")
    private List<StudentAttendance> attendances;



}
