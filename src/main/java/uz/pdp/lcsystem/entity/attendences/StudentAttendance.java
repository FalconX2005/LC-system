package uz.pdp.lcsystem.entity.attendences;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import uz.pdp.lcsystem.entity.Group;
import uz.pdp.lcsystem.entity.Student;
import uz.pdp.lcsystem.entity.tempAbs.AbsLongEntity;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class StudentAttendance extends AbsLongEntity {
    @ManyToOne
    private Student student;

    @ManyToOne
    private Group group;

    private LocalDate attendanceDate;

    private boolean status;

}
