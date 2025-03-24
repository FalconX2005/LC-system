package uz.pdp.lcsystem.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.lcsystem.enums.Days;
import uz.pdp.lcsystem.enums.Status;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
/*
public class GroupDTO {
    private Long id;
    private String name;
    private Long courseId;
    private Long employeeId;
    private List<StudentDto> students;
    private Long roomId;
    private Long stNumber;
    private Days   days;
    private Timestamp startTime;
    private Timestamp endTime;
    private LocalDate startDate;
    private LocalDate endDate;
    private Status  status;



}
*/

public class GroupDTO {
    private Long id;
    private String groupName;
    private String employeeName;
    private String courseName;
    private String roomName;
    private Long studentCount;
    private Timestamp startTime;
    private Timestamp endTime;
    private LocalDate startDate;
    private LocalDate endDate;
    private Status status;
}
