package uz.pdp.lcsystem.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StudentAttendanceDTO {

    private Long id ;

    private Long studentId;

    private Long groupId;

    private LocalDate attendanceDate;

    private Boolean status;



}
