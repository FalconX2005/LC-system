package uz.pdp.lcsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.lcsystem.payload.ApiResult;
import uz.pdp.lcsystem.payload.GroupDTO;
import uz.pdp.lcsystem.service.SearchService;

import java.util.List;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {

   private final SearchService searchService;

   @GetMapping("/search")
    public ApiResult<List<GroupDTO>> searchGroups(@RequestParam String name) {
        List<GroupDTO> result = searchService.searchGroup(name);
        return ApiResult.success(result);
    }

}
