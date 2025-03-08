package uz.pdp.lcsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.lcsystem.entity.attendences.TeacherAttendance;
import uz.pdp.lcsystem.entity.tempAbs.AbsLongEntity;
import uz.pdp.lcsystem.enums.Gender;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Employee  extends AbsLongEntity {

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany
    private List<Group> groups;

    private Long salary;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "employee")
    @ToString.Exclude
    private List<TeacherAttendance> attendances;
}
