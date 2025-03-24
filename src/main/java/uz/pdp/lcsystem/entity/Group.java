package uz.pdp.lcsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import uz.pdp.lcsystem.entity.tempAbs.AbsLongEntity;
import uz.pdp.lcsystem.enums.Days;
import uz.pdp.lcsystem.enums.Status;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity(name = "groups")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@SQLDelete(sql = "UPDATE groups SET deleted = true WHERE id = ?")
public class Group extends AbsLongEntity {
    private String groupName;

    @ManyToOne
    private Course course;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Employee employee;

    @ToString.Exclude
    @OneToMany(mappedBy = "group")
    private List<EmployeeGroup> groups;



    @ManyToOne
    private Room room;

//    @OneToMany(mappedBy = "group",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    private List<Student> students;

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
