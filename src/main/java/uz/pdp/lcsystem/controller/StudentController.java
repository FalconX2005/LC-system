package uz.pdp.lcsystem.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import uz.pdp.lcsystem.payload.ApiResult;

import uz.pdp.lcsystem.payload.StudentDTO;
import uz.pdp.lcsystem.service.SearchService;
import uz.pdp.lcsystem.service.StudentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private final SearchService searchService;

    @GetMapping
    public ApiResult<List<StudentDTO>> getStudents() {
        List<StudentDTO> all = studentService.getAll();
        return ApiResult.success(all);
    }

    @GetMapping("/{id}")
    public ApiResult<StudentDTO> getById(@PathVariable Long id) {
        StudentDTO byId = studentService.getById(id);
        return ApiResult.success(byId);
    }

    @GetMapping("/search")
    public ApiResult<List<StudentDTO>> searchStudents(@RequestParam String name) {
        List<StudentDTO> result = searchService.searchStudent(name);
        return ApiResult.success(result);
    }

    @PostMapping("/create")
    public ApiResult<StudentDTO> create(@RequestBody StudentDTO studentDto) {
        StudentDTO result = studentService.create(studentDto);
        return ApiResult.success(result);
    }

    @PutMapping("/update/{id}")
    public ApiResult<StudentDTO> update(@PathVariable Long id, @RequestBody StudentDTO studentDto) {
        StudentDTO result = studentService.update(id, studentDto);
        return ApiResult.success(result);
    }

    @DeleteMapping("/{id}")
    public ApiResult<StudentDTO> delete(@PathVariable Long id) {
        StudentDTO result = studentService.delete(id);
        return ApiResult.success(result);
    }

}
