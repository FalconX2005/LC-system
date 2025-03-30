package uz.pdp.lcsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lcsystem.payload.ApiResult;
import uz.pdp.lcsystem.payload.RoomDTO;
import uz.pdp.lcsystem.payload.withoutId.RoomDto;
import uz.pdp.lcsystem.service.RoomService;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.lcsystem.service.SearchService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/room")
@RestController
public class RoomController {

    private final RoomService roomService;

    @Secured({"ADMIN","MANAGER"})
    @GetMapping
    public ApiResult<List<RoomDTO>> getAllEmployees() {
        List<RoomDTO> all = roomService.getAll();
        return ApiResult.success(all);
    }

    @Secured({"ADMIN","MANAGER"})
    @GetMapping("/{id}")
    public ApiResult<RoomDTO> getEmployeeById(@PathVariable Long id) {
        RoomDTO byId = roomService.getById(id);
        return ApiResult.success(byId);
    }
    @Secured({"ADMIN"})
    @PostMapping("/create")
    public ApiResult<RoomDTO> createRoom(@RequestBody RoomDto roomDto) {
        RoomDTO roomDto1 = roomService.create(roomDto);
        return ApiResult.success(roomDto1);
    }

    @Secured({"ADMIN"})
    @PutMapping("/update/{id}")
    public ApiResult<RoomDTO> update(@PathVariable Long id,@RequestBody RoomDto roomDto){

        RoomDTO roomDto1 = roomService.update(id,roomDto);
        return ApiResult.success(roomDto1);
    }

    @Secured({"ADMIN"})
    @DeleteMapping("/{id}")
    public ApiResult<RoomDTO> delete(@PathVariable Long id) {
        RoomDTO roomDto = roomService.delete(id);
        return ApiResult.success(roomDto);
    }
    private final SearchService searchService;

    @GetMapping("/search")
    public ApiResult<List<RoomDTO>> searchRooms(@RequestParam String name) {
        List<RoomDTO> result = searchService.searchRoom(name);
        return ApiResult.success(result);
    }

}


