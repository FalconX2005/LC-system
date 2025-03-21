package uz.pdp.lcsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
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
@SQLDelete(sql = "UPDATE employee SET deleted = true WHERE id = ?")
public class Employee  extends AbsLongEntity {

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ToString.Exclude
    @OneToMany(mappedBy = "employee")
    private List<EmployeeGroup> groups;



    //    @OneToMany(mappedBy = "employee")
//    private List<Group> groups;

    private Long salary;
    @OneToOne
    private User user;

    private List<TeacherAttendance> attendances;
}
