package uz.pdp.lcsystem.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.annotation.Secured;
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

    @Secured({"ADMIN","MANAGER","TEACHER"})
    @GetMapping
    public ApiResult<List<StudentDTO>> getStudents() {
        List<StudentDTO> all = studentService.getAll();
        return ApiResult.success(all);
    }

    @Secured({"ADMIN","MANAGER","TEACHER"})
    @GetMapping("/{id}")
    public ApiResult<StudentDTO> getById(@PathVariable Long id) {
        StudentDTO byId = studentService.getById(id);
        return ApiResult.success(byId);
    }

    @Secured({"ADMIN","MANAGER","TEACHER"})
    @GetMapping("/search")
    public ApiResult<List<StudentDTO>> searchStudents(@RequestParam String name) {
        List<StudentDTO> result = searchService.searchStudent(name);
        return ApiResult.success(result);
    }
    @Secured({"ADMIN","MANAGER"})
    @PostMapping("/create")
    public ApiResult<StudentDTO> create(@RequestBody StudentDTO studentDto) {
        StudentDTO result = studentService.create(studentDto);
        return ApiResult.success(result);
    }

    @Secured({"ADMIN","MANAGER"})
    @PutMapping("/update/{id}")
    public ApiResult<StudentDTO> update(@PathVariable Long id, @RequestBody StudentDTO studentDto) {
        StudentDTO result = studentService.update(id, studentDto);
        return ApiResult.success(result);
    }

    @Secured({"ADMIN","MANAGER"})
    @DeleteMapping("/{id}")
    public ApiResult<StudentDTO> delete(@PathVariable Long id) {
        StudentDTO result = studentService.delete(id);
        return ApiResult.success(result);
    }

}
