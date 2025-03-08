package uz.pdp.lcsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.lcsystem.entity.tempAbs.AbsLongEntity;
import uz.pdp.lcsystem.enums.Days;
import uz.pdp.lcsystem.enums.Status;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Group extends AbsLongEntity {
    private String groupName;

    @ManyToOne
    private Course course;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Room room;

    @OneToMany
    private List<Student> students;

    private Long stNumber;

    @Enumerated(EnumType.STRING)
    private Days days;

    private Timestamp startTime;

    private Timestamp endTime;

    private LocalDate startDate;

    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private Status status;



}
