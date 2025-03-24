package uz.pdp.lcsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.lcsystem.entity.Room;
import uz.pdp.lcsystem.payload.ApiResult;
import uz.pdp.lcsystem.payload.RoomDto;
import uz.pdp.lcsystem.repository.RoomRepository;
import uz.pdp.lcsystem.service.RoomService;
import uz.pdp.lcsystem.service.SearchService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/room")
@RestController
public class RoomController {

    private final SearchService searchService;

    @GetMapping("/search")
    public ApiResult<List<RoomDto>> searchRooms(@RequestParam String name) {
        List<RoomDto> result = searchService.searchRoom(name);
        return ApiResult.success(result);
    }

}


