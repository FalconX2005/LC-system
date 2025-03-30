package uz.pdp.lcsystem.payload.withoutId;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoomDto implements Serializable {


    @NotBlank(message = "Room name bush bulishi mumkun emas!")
    String name;

    @NotNull(message = "capasity bush bulishi mumkun emas!")
    @Positive(message = "capasity musbat bulishi kerak")
    Integer capacity;

    @Positive(message = "stollar soni bush bulishi mumkun emas!")
    @NotNull(message = "stollar soni bush bulishi mumkun emas")
    Integer countOfTable;

    @NotNull(message = "stullar soni bush bulishi mumkun emas!")
    @Positive(message = "stullar soni musbat bulishi kerak")
    Integer countOfChair;
}