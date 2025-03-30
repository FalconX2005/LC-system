package uz.pdp.lcsystem.payload.withoutId;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CourseDto {


    @NotBlank(message = "name bush bulishi mumkun emas")
    private String name;

    @NotNull(message = "price bush bulishi kerak emas")
    @Positive(message = "price musbat bulishi kerak")
    private Long price;

}
