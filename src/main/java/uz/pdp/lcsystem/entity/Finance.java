package uz.pdp.lcsystem.entity;

import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import uz.pdp.lcsystem.entity.tempAbs.AbsLongEntity;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "finance")
@Builder
@SQLDelete(sql = "UPDATE finance SET deleted = true WHERE id = ?")
public class Finance extends AbsLongEntity {

    private String name;

    private LocalDate date;

    private String category;

    private String receiver;

    private Double amount;

    private boolean paid;



}
