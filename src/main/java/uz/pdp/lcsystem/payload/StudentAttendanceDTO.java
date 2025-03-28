
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
public class StudentAttendanceDTO {

    private Long id ;

    @NotBlank(message = "studentName bush bulishi mumkun emas!")
    private String studentName;

    @NotNull(message = "studentId bush bulishi mumkun emas!")
    private Long studentId;

    @NotNull(message = "groupId bush bulishi mumkun emas!")
    private Long groupId;

    @NotNull(message = "attendanceDate bush bulishi mumkun emas")
    @FutureOrPresent(message = "Davomat sanasi o‘tgan bo‘lishi mumkin emas")
    private LocalDate attendanceDate;

    @NotNull(message = "Davomat holati bo‘sh bo‘lishi mumkin emas")
    private Boolean status;



}
