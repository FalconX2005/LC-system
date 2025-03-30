package uz.pdp.lcsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.lcsystem.entity.Room;
import uz.pdp.lcsystem.exception.RestException;
import uz.pdp.lcsystem.payload.RoomDTO;
import uz.pdp.lcsystem.payload.withoutId.RoomDto;
import uz.pdp.lcsystem.repository.RoomRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {

    @Autowired
    private final RoomRepository roomRepository;

    public List<RoomDTO> getAll() {

        List<Room> all = roomRepository.findAll();
        List<RoomDTO> roomDtoList = new ArrayList<RoomDTO>();
        if (all.isEmpty()) {
            throw RestException.error("Room not found");
        }
        for (Room room : all) {
          if (!room.isDeleted()) {
              RoomDTO build = RoomDTO.builder()
                      .id(room.getId())
                      .capacity(room.getCapacity())
                      .name(room.getName())
                      .countOfChair(room.getCountOfChair())
                      .countOfTable(room.getCountOfTable())
                      .build();
              roomDtoList.add(build);
          }
        }


        return roomDtoList;
    }

    public RoomDTO getById(Long id) {
        Optional<Room> byId = roomRepository.findById(id);
        if (!byId.isPresent()) {
            throw RestException.notFound("Room not found",id);
        }
        Room room = byId.get();
        RoomDTO build = RoomDTO.builder()
                .id(room.getId())
                .capacity(room.getCapacity())
                .name(room.getName())
                .countOfChair(room.getCountOfChair())
                .countOfTable(room.getCountOfTable())
                .build();
        return build;

    }

    @Transactional
    public RoomDTO create(RoomDto roomDto) {
        List<Room> byName = roomRepository.findByName(roomDto.getName());
        if (!byName.isEmpty()) {
            throw RestException.error("Room already exists");
        }
        Room build = Room.builder()
                .capacity(roomDto.getCapacity())
                .countOfChair(roomDto.getCountOfChair())
                .countOfTable(roomDto.getCountOfTable())
                .name(roomDto.getName())
                .build();
        roomRepository.save(build);
        RoomDTO build1 = RoomDTO.builder()
                .id(build.getId())
                .capacity(build.getCapacity())
                .countOfChair(build.getCountOfChair())
                .countOfTable(build.getCountOfTable())
                .name(build.getName())
                .build();
        return build1;

    }

    @Transactional
    public RoomDTO update(Long id, RoomDto roomDto) {
        Optional<Room> byId = roomRepository.findById(id);
        RoomDTO roomDTO = new RoomDTO();
        if (!byId.isPresent()) {
            throw RestException.notFound("Room not found: ",id);
        }
        Room room = byId.get();
        room.setName(roomDto.getName());
        room.setCapacity(roomDto.getCapacity());
        room.setCountOfChair(roomDto.getCountOfChair());
        room.setCountOfTable(roomDto.getCountOfTable());
        Room save = roomRepository.save(room);
        roomDTO.setId(save.getId());
        roomDTO.setName(save.getName());
        roomDTO.setCapacity(save.getCapacity());
        roomDTO.setCountOfChair(save.getCountOfChair());
        roomDTO.setCountOfTable(save.getCountOfTable());
        return roomDTO;
    }
    @Transactional
    public RoomDTO delete(Long id) {
        Optional<Room> byId = roomRepository.findById(id);
        if (!byId.isPresent()) {
            throw RestException.notFound("Room not found",id);
        }
        Room room = byId.get();
        RoomDTO build = RoomDTO.builder().id(room.getId())
                .countOfTable(room.getCountOfTable())
                .countOfChair(room.getCountOfChair())
                .capacity(room.getCapacity())
                .name(room.getName())
                .build();
        roomRepository.delete(room);
        return build;
    }
}
