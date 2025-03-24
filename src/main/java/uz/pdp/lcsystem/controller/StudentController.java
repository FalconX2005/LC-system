package uz.pdp.lcsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.lcsystem.payload.ApiResult;
import uz.pdp.lcsystem.payload.StudentDto;
import uz.pdp.lcsystem.service.SearchService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final SearchService searchService;

    @GetMapping("/search")
    public ApiResult<List<StudentDto>> searchStudents(@RequestParam String name) {
        List<StudentDto> result = searchService.searchStudent(name);

        return ApiResult.success(result);
    }
}
