package uz.pdp.lcsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.pdp.lcsystem.entity.Course;
import uz.pdp.lcsystem.exception.RestException;
import uz.pdp.lcsystem.payload.ApiResult;
import uz.pdp.lcsystem.payload.CourseDTO;
import uz.pdp.lcsystem.repository.CourseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {
    private CourseRepository courseRepository;

    public List<CourseDTO> getAllCourseList() {
        List<CourseDTO> courseDTOList = new ArrayList<>();
        List<Course> all = courseRepository.findAll();
        if (all.isEmpty()) {
            throw RestException.error("No courses found");
        }
        for (Course course : all) {
            CourseDTO build = CourseDTO.builder()
                    .id(course.getId())
                    .name(course.getCourseName())
                    .price(course.getPrice())
                    .build();

            courseDTOList.add(build);
        }
        return courseDTOList;
    }

    public CourseDTO getCourseById(Long id) {
        Optional<Course> byId = courseRepository.findById(id);
        if (byId.isPresent()) {
            Course course = byId.get();
            CourseDTO result = CourseDTO.builder()
                    .name(course.getCourseName())
                    .price(course.getPrice())
                    .id(course.getId())
                    .build();
            return result;
        }
        throw RestException.notFound("course not found", HttpStatus.NOT_FOUND);
    }

    public CourseDTO createCourse(CourseDTO courseDTO) {


        Course build = Course.builder()
                .courseName(courseDTO.getName())
                .price(courseDTO.getPrice())
                .build();
        courseRepository.save(build);
        return courseDTO;
    }
    public List<CourseDTO> searchCourses(String query) {
        List<CourseDTO> courseDTOList = new ArrayList<>();
        List<Course> courses = courseRepository.findByCourseNameContainingIgnoreCase(query);

        for (Course course : courses) {
            CourseDTO dto = CourseDTO.builder()
                    .id(course.getId())
                    .name(course.getCourseName())
                    .price(course.getPrice())
                    .build();
            courseDTOList.add(dto);
        }
        return courseDTOList;
    }
    public List<Course> searchCoursesByTitle(String keyword) {
        String formattedKeyword = keyword.replace(" ", " & "); // PostgreSQL search formatiga moslash
        return courseRepository.searchByTitle(formattedKeyword);
    }

    public boolean deleteCourseById(Long id) {
        Optional<Course> byId = courseRepository.findById(id);
        if (byId.isPresent()) {
            courseRepository.delete(byId.get());
            return true;
        }
        throw RestException.notFound("course not found", HttpStatus.NOT_FOUND);

    }
}
