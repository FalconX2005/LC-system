package uz.pdp.lcsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.lcsystem.payload.ApiResult;
import uz.pdp.lcsystem.payload.EmployeeDTO;
import uz.pdp.lcsystem.service.SearchService;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final SearchService searchService;

    @GetMapping("/search")
    public ApiResult<List<EmployeeDTO>> searchEmployees(@RequestParam String name) {
        List<EmployeeDTO> result = searchService.searchEmployee(name);
        return ApiResult.success(result);
    }

}

