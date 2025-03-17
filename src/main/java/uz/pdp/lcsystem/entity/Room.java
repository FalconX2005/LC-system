package uz.pdp.lcsystem.entity;

import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import uz.pdp.lcsystem.entity.tempAbs.AbsLongEntity;

import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@SQLDelete(sql = "UPDATE room SET deleted = true WHERE id = ?")
public class Room extends AbsLongEntity {

    private String name;

    private Integer capacity;

    private Integer countOfTable;

    private Integer countOfChair;


}
