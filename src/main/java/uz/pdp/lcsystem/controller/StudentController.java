package uz.pdp.lcsystem.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import uz.pdp.lcsystem.payload.ApiResult;
import uz.pdp.lcsystem.payload.StudentDto;
import uz.pdp.lcsystem.service.StudentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private final SearchService searchService;

    @GetMapping
    public ApiResult<List<StudentDto>> getStudents() {
        List<StudentDto> all = studentService.getAll();
        return ApiResult.success(all);
    }

    @GetMapping("/{id}")
    public ApiResult<StudentDto> getById(@PathVariable Long id) {
        StudentDto byId = studentService.getById(id);
        return ApiResult.success(byId);
    }

    @GetMapping("/search")
    public ApiResult<List<StudentDto>> searchStudents(@RequestParam String name) {
        List<StudentDto> result = searchService.searchStudent(name);
        return ApiResult.success(result);
    }

    @PostMapping("/create")
    public ApiResult<StudentDto> create(@RequestBody StudentDto studentDto) {
        StudentDto result = studentService.create(studentDto);
        return ApiResult.success(result);
    }

    @PutMapping("/update/{id}")
    public ApiResult<StudentDto> update(@PathVariable Long id, @RequestBody StudentDto studentDto) {
        StudentDto result = studentService.update(id, studentDto);
        return ApiResult.success(result);
    }

    @DeleteMapping("/{id}")
    public ApiResult<StudentDto> delete(@PathVariable Long id) {
        StudentDto result = studentService.delete(id);
        return ApiResult.success(result);
    }

}
