package uz.pdp.lcsystem.payload.withoutId;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.lcsystem.entity.Attachment;
import uz.pdp.lcsystem.enums.Gender;
import uz.pdp.lcsystem.enums.RoleEnum;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeDto{



    @NotBlank(message = "firstName bush bulishi mumkun emas")
    private String firstName;

    @NotBlank(message = "lastName bush bulishi mumkun emas")
    private String lastName;

    @Past(message = "Tug'ilgan sana o'tgan sanada bulishi kerak")
    @NotNull(message = "bush bulishi mumkun emas")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @NotBlank(message = "phoneNumber bush bulishi mumkun emas")
    @Pattern(regexp = "\\+998\\d{9}",message = "tel nomer formati notug'ri Masalan: +998901234567")
    private String phoneNumber;

    @NotNull(message = "gender bush bulishi mumkun emas")
    private Gender gender;


    @Positive(message = "salary musbat bulishi kerak")
    @NotNull(message = "salary bush bulishi kerak emas")
    private Long salary;


    private UserDto user;

    @NotNull(message = "attachmentId bush bulishi mumkun emas")
    private Long attachmentId;

}
