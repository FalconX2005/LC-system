package uz.pdp.lcsystem.entity;

import jakarta.persistence.Entity;
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
public class Room extends AbsLongEntity {

    private String name;

    private Integer capacity;

    private Integer countOfTable;

    private Integer countOfChair;


}
