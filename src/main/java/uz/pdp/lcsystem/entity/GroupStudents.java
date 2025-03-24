package uz.pdp.lcsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import uz.pdp.lcsystem.entity.tempAbs.AbsLongEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class GroupStudents extends AbsLongEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;


    @ManyToOne(fetch = FetchType.LAZY)
    private Group group;
}