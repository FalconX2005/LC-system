package uz.pdp.lcsystem.payload;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TeacherAttendanceDTO {

    private Long id;

    @NotNull(message = "teacherId bush bulishi mumkun emas!")
    private Long teacherId;

    @NotBlank(message = "teacherName bush bulishi mumkun emas!")
    private String teacherName;

    @NotNull(message = "groupId bush bulishi mumkun emas")
    private Long groupId;

    @FutureOrPresent(message = "Davomat sanasi o‘tmishda bo‘lishi mumkin emas")
    @NotNull(message = "Davomat sanasi bo‘sh bo‘lishi mumkin emas")
    private LocalDate attendanceDate;

    private boolean status;


}
