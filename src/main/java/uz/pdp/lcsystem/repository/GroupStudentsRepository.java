package uz.pdp.lcsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lcsystem.entity.GroupStudents;
import uz.pdp.lcsystem.entity.Student;

import java.util.List;

public interface GroupStudentsRepository extends JpaRepository<GroupStudents, Long> {
    List<GroupStudents> findByStudentId(Long studentId);

    List<GroupStudents> findByGroupId(Long groupId);


}