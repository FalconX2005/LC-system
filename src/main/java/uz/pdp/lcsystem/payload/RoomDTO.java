package uz.pdp.lcsystem.payload;

import lombok.*;
import uz.pdp.lcsystem.entity.Room;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoomDTO implements Serializable {
    Long id;
    String name;
    Integer capacity;
    Integer countOfTable;
    Integer countOfChair;
}