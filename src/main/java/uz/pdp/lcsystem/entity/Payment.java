package uz.pdp.lcsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import uz.pdp.lcsystem.entity.tempAbs.AbsLongEntity;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Payment extends AbsLongEntity {

    private Double amount;

    private LocalDate paymentDate;

    private boolean paid;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Group group;



}
