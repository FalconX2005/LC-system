package uz.pdp.lcsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lcsystem.payload.ApiResult;
import uz.pdp.lcsystem.payload.CourseDTO;
import uz.pdp.lcsystem.service.CourseService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("course")
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public ApiResult<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> allCourseList = courseService.getAllCourseList();
        return ApiResult.success(allCourseList);
    }


    @GetMapping("/{id}")
    public ApiResult<CourseDTO> getCourseById(@PathVariable Long id) {
        CourseDTO courseById = courseService.getCourseById(id);
        return ApiResult.success(courseById);
    }

    @PostMapping("/create")
    public ApiResult<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) {
        CourseDTO course = courseService.createCourse(courseDTO);
        return ApiResult.success("course created");
    }

    @DeleteMapping("/{id}")
    public ApiResult<CourseDTO> deleteCourse(@PathVariable Long id) {
        boolean b = courseService.deleteCourseById(id);
        return ApiResult.success("course deleted");
    }


}
