package uz.pdp.lcsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.lcsystem.entity.tempAbs.AbsLongEntity;
import uz.pdp.lcsystem.enums.Days;
import uz.pdp.lcsystem.enums.Status;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder@Entity(name = "groups")
public class Group extends AbsLongEntity {
    private String groupName;

    @ManyToOne
    private Course course;

    @ToString.Exclude
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EmployeeGroup> employeeGroups;  // O‘zgaruvchini ham to‘g‘ri nomladim

    @ManyToOne
    private Room room;

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
