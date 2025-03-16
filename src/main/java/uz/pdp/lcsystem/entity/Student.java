package uz.pdp.lcsystem.entity;

import jakarta.persistence.*;
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

    @ToString.Exclude
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<GroupStudents> groupStudents;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne
    private User user;

    @ToString.Exclude
    @OneToMany(mappedBy = "student")
    private List<StudentAttendance> attendances;



}
