package uz.pdp.lcsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.pdp.lcsystem.entity.Course;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
        List<Course> findByCourseNameContainingIgnoreCase(String query);
        /*@Query(value = "SELECT * FROM courses WHERE to_tsvector('simple', title) @@ to_tsquery(:keyword)", nativeQuery = true)
        List<Course> searchByTitle(@Param("keyword") String keyword);
*/}