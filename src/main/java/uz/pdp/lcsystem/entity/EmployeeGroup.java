package uz.pdp.lcsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import uz.pdp.lcsystem.entity.tempAbs.AbsLongEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@SQLDelete(sql = "UPDATE employee_group SET deleted = true WHERE id = ?")
public class EmployeeGroup extends AbsLongEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;


    @ManyToOne(fetch = FetchType.LAZY)
    private Group group;
}
