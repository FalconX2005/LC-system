package uz.pdp.lcsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lcsystem.payload.ApiResult;
import uz.pdp.lcsystem.payload.EmployeeDTO;
import uz.pdp.lcsystem.payload.RoomDto;
import uz.pdp.lcsystem.service.RoomService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/room")
@RestController
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    public ApiResult<List<RoomDto>> getAllEmployees() {
        List<RoomDto> all = roomService.getAll();
        return ApiResult.success(all);
    }

    @GetMapping("/{id}")
    public ApiResult<RoomDto> getEmployeeById(@PathVariable Long id) {
        RoomDto byId = roomService.getById(id);
        return ApiResult.success(byId);
    }
    @PostMapping("/create")
    public ApiResult<RoomDto> createRoom(@RequestBody RoomDto roomDto) {
        RoomDto roomDto1 = roomService.create(roomDto);
        return ApiResult.success(roomDto1);
    }

    @PutMapping("/update")
    public ApiResult<RoomDto> update(@RequestBody RoomDto roomDto){

        RoomDto roomDto1 = roomService.update(roomDto);
        return ApiResult.success(roomDto1);
    }

    @DeleteMapping("/{id}")
    public ApiResult<RoomDto> delete(@PathVariable Long id) {
        RoomDto roomDto = roomService.delete(id);
        return ApiResult.success(roomDto);
    }
}


