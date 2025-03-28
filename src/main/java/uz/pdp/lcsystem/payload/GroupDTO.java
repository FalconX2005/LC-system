package uz.pdp.lcsystem.payload;

import jakarta.validation.constraints.*;
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

public class GroupDTO {

    private Long id;

    @NotBlank(message = "name bush bulishi mumkun emas")
    private String name;

    @NotNull(message = "courseId bush bulishi kerak emas")
    @Positive(message = "courseId musbat bulishi kerak")
    private Long courseId;

    @NotNull(message = "roomId bush bulishi mumkun emas")
    @Positive(message = "roomId musbat bulishi kerak")
    private Long roomId;

    @NotNull(message = "stNumber bush bulishi mumkun emas")
    @Positive(message = "stNumber musbat bulishi kerak")
    private Long stNumber;

    @NotNull(message = "days tanlanish kerak!")
    private Days   days;

    @FutureOrPresent(message = "dars boshlanish vaqti hozir yoki kelajak bulishi kerak")
    @NotNull(message = "startTime bush bulishi mumkun emas")
    private Timestamp startTime;

    @Future(message = "dars tugash vaqti kelajak bulishi kerak")
    @NotNull(message = "endTime bush bulishi mumkun emas")
    private Timestamp endTime;

    @FutureOrPresent(message = "boshlanish sana bugun yoki kelajak bulishi kerak")
    @NotNull(message = "startDate bush bulmasin!")
    private LocalDate startDate;

    @Future(message = "endDate kelajak bulishi kerak")
    @NotNull(message = "endDate bush bulishi mumkun emas!")
    private LocalDate endDate;

    @NotNull(message = "status mush bulishi mumkun emas")
    private Status  status;

}




