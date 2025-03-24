package uz.pdp.lcsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.pdp.lcsystem.entity.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    /*@Query(value = "SELECT * FROM students WHERE to_tsvector('english', first_name || ' ' || last_name) @@ to_tsquery(:query)", nativeQuery = true)
    List<Student> searchStudents(@Param("query") String query);
*/
}