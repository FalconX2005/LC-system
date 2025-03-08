package uz.pdp.lcsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.lcsystem.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}