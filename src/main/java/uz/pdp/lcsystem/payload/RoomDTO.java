package uz.pdp.lcsystem.payload;

import lombok.Value;
import uz.pdp.lcsystem.entity.Room;

import java.io.Serializable;

/**
 * DTO for {@link Room}
 */
@Value
public class RoomDTO implements Serializable {
    Long id;
    String name;
    Integer capacity;
    Integer countOfTable;
    Integer countOfChair;
}