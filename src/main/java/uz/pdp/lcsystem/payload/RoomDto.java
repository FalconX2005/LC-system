package uz.pdp.lcsystem.payload;

import lombok.*;
import uz.pdp.lcsystem.entity.Room;

import java.io.Serializable;

/**
 * DTO for {@link Room}
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoomDto implements Serializable {
    Long id;
    String name;
    Integer capacity;
    Integer countOfTable;
    Integer countOfChair;
}