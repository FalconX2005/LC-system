package uz.pdp.lcsystem.entity.attendences;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import uz.pdp.lcsystem.entity.Employee;
import uz.pdp.lcsystem.entity.tempAbs.AbsLongEntity;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class TeacherAttendance extends AbsLongEntity {

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Group group;

    private LocalDate attendanceDate;

    private boolean status;


}
