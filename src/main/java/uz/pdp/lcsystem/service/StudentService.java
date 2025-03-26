package uz.pdp.lcsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.lcsystem.entity.Student;
import uz.pdp.lcsystem.entity.User;
import uz.pdp.lcsystem.exception.RestException;
import uz.pdp.lcsystem.payload.StudentDTO;
import uz.pdp.lcsystem.repository.GroupStudentsRepository;
import uz.pdp.lcsystem.repository.StudentAttendanceRepository;
import uz.pdp.lcsystem.repository.StudentRepository;
import uz.pdp.lcsystem.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    @Autowired
    private final GroupStudentsRepository groupStudentsRepository;

    @Autowired
    private final StudentRepository studentRepository;
    @Autowired
    private final StudentAttendanceRepository studentAttendanceRepository;
    @Autowired
    private final UserRepository userRepository;


    public List<StudentDTO> getAll() {
        List<Student> students = studentRepository.findAll();

        List<StudentDTO> result = new ArrayList<StudentDTO>();

        for (Student student : students) {

            StudentDTO build = StudentDTO.builder()
                    .id(student.getId()).
                    firstName(student.getFirstName())
                    .lastName(student.getLastName())
                    .username(student.getUser().getUsername())
                    .email(student.getUser().getEmail())
                    .role(student.getUser().getRoleEnum())
                    .gender(student.getGender())
//                    .attendances(student.getAttendances())

                    .build();
            result.add(build);
        }
        return result;
    }


    public StudentDTO getById(@PathVariable Long id) {


        Optional<Student> byId = studentRepository.findById(id);

        if (byId.isPresent()) {
            Student student = byId.get();
            StudentDTO build = StudentDTO.builder()
                    .gender(student.getGender())
                    .firstName(student.getFirstName())
                    .lastName(student.getLastName())
                    .id(student.getId())
                    .username(student.getUser().getUsername())
                    .email(student.getUser().getEmail())
                    .role(student.getUser().getRoleEnum())
//                    .attendances(student.getAttendances())
                    .build();
            return build;
        }
        throw RestException.error("Student not found");
    }


    @Transactional
    public StudentDTO create(@RequestBody StudentDTO studentDto) {

        User build = User.builder()
                .username(studentDto.getUsername())
                .password(studentDto.getPassword())
                .email(studentDto.getEmail())
                .roleEnum(studentDto.getRole())
                .build();
        Student result = Student.builder()
                .firstName(studentDto.getFirstName())
                .lastName(studentDto.getLastName())
                .gender(studentDto.getGender())
                .user(build)
                .build();

        userRepository.save(build);
        studentRepository.save(result);
        return studentDto;
    }

    @Transactional
    public StudentDTO update(@PathVariable Long id, @RequestBody StudentDTO studentDto) {
        Optional<User> findByUsername = userRepository.findByUsername(studentDto.getUsername());
        if (findByUsername.isPresent()) {
            User byUsername = findByUsername.get();
            byUsername.setEmail(studentDto.getEmail());
            byUsername.setUsername(studentDto.getUsername());
            byUsername.setPassword(studentDto.getPassword());
            byUsername.setRoleEnum(studentDto.getRole());


            Optional<Student> byId = studentRepository.findById(id);
            if (byId.isPresent()) {
                Student student = byId.get();
                student.setFirstName(studentDto.getFirstName());
                student.setLastName(studentDto.getLastName());
                student.setGender(studentDto.getGender());
                student.setUser(byUsername);
                userRepository.save(byUsername);
                studentRepository.save(student);
                return studentDto;
            }
            throw RestException.error("Student not found");
        }

        throw RestException.error("Student not found");
    }

    @Transactional
    public StudentDTO delete(@PathVariable Long id) {

        Optional<Student> byId = studentRepository.findById(id);
        if (byId.isPresent()) {
            Student student = byId.get();
            userRepository.delete(student.getUser());
            studentRepository.delete(student);


            StudentDTO result = StudentDTO.builder()
                    .id(student.getId())
                    .firstName(student.getFirstName())
                    .lastName(student.getLastName())
                    .gender(student.getGender())
                    .username(student.getUser().getUsername())
                    .email(student.getUser().getEmail())
                    .role(student.getUser().getRoleEnum())
                    .build();
            return result;
        }
        throw RestException.error("Student not found");
    }


}
