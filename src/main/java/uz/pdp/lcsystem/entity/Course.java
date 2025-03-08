package uz.pdp.lcsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import uz.pdp.lcsystem.entity.tempAbs.AbsLongEntity;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Course extends AbsLongEntity {

    private String courseName;

    private Long price;

    @OneToMany(mappedBy = "course")
    private List<Group> groups;
}
