
package uz.pdp.lcsystem.payload.withoutId;

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
public class StudentAttendanceDto{


    @NotNull(message = "studentId bush bulishi mumkun emas!")
    private Long studentId;

    @NotNull(message = "groupId bush bulishi mumkun emas!")
    private Long groupId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    @NotNull(message = "attendanceDate bush bulishi mumkun emas")
    @FutureOrPresent(message = "Davomat sanasi o‘tgan bo‘lishi mumkin emas")
    private LocalDate attendanceDate;

    @NotNull(message = "Davomat holati bo‘sh bo‘lishi mumkin emas")
    private Boolean status;



}
