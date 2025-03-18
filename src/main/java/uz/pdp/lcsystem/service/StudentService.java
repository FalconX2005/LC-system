package uz.pdp.lcsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lcsystem.entity.Student;
import uz.pdp.lcsystem.entity.attendences.StudentAttendance;
import uz.pdp.lcsystem.payload.StudentDto;
import uz.pdp.lcsystem.repository.StudentAttendanceRepository;
import uz.pdp.lcsystem.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;
    @Autowired
    private final StudentAttendanceRepository studentAttendanceRepository;


    public List<StudentDto> getAll(StudentDto studentDto) {
        List<Student> students = studentRepository.findAll();

        List<StudentDto> result = new ArrayList<StudentDto>();

        for (Student student : students) {

            StudentDto build = StudentDto.builder()
                    .id(student.getId()).
                    firstName(student.getFirstName())
                    .lastName(student.getLastName())
                    .userId(student.getUser().getId())
                    .gender(student.getGender())
//                    .attendances(student.getAttendances())

                    .build();
            result.add(build);
        }
        return result;
    }

}
