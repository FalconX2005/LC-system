package uz.pdp.lcsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lcsystem.payload.ApiResult;
import uz.pdp.lcsystem.payload.EmployeeDTO;
import uz.pdp.lcsystem.payload.RoomDTO;
import uz.pdp.lcsystem.service.RoomService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/room")
@RestController
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    public ApiResult<List<RoomDTO>> getAllEmployees() {
        List<RoomDTO> all = roomService.getAll();
        return ApiResult.success(all);
    }

    @GetMapping("/{id}")
    public ApiResult<RoomDTO> getEmployeeById(@PathVariable Long id) {
        RoomDTO byId = roomService.getById(id);
        return ApiResult.success(byId);
    }
    @PostMapping("/create")
    public ApiResult<RoomDTO> createRoom(@RequestBody RoomDTO roomDto) {
        RoomDTO roomDto1 = roomService.create(roomDto);
        return ApiResult.success(roomDto1);
    }

    @PutMapping("/update")
    public ApiResult<RoomDTO> update(@RequestBody RoomDTO roomDto){

        RoomDTO roomDto1 = roomService.update(roomDto);
        return ApiResult.success(roomDto1);
    }

    @DeleteMapping("/{id}")
    public ApiResult<RoomDTO> delete(@PathVariable Long id) {
        RoomDTO roomDto = roomService.delete(id);
        return ApiResult.success(roomDto);
    }
}


