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
import uz.pdp.lcsystem.payload.StudentDto;
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


    public List<StudentDto> getAll() {
        List<Student> students = studentRepository.findAll();

        List<StudentDto> result = new ArrayList<StudentDto>();

        for (Student student : students) {

            StudentDto build = StudentDto.builder()
                    .id(student.getId()).
                    firstName(student.getFirstName())
                    .lastName(student.getLastName())
                    .username(student.getUser().getUsername())
                    .email(student.getUser().getEmail())
                    .role(student.getUser().getRoleEnum())
                    .gender(student.getGender())
                    .phoneNumber(student.getPhoneNumber())
//                    .attendances(student.getAttendances())

                    .build();
            result.add(build);
        }
        return result;
    }


    public StudentDto getById(@PathVariable Long id) {


        Optional<Student> byId = studentRepository.findById(id);

        if (byId.isPresent()) {
            Student student = byId.get();
            StudentDto build = StudentDto.builder()
                    .gender(student.getGender())
                    .firstName(student.getFirstName())
                    .lastName(student.getLastName())
                    .id(student.getId())
                    .username(student.getUser().getUsername())
                    .email(student.getUser().getEmail())
                    .role(student.getUser().getRoleEnum())
                    .phoneNumber(student.getPhoneNumber())
//                    .attendances(student.getAttendances())
                    .build();
            return build;
        }
        throw RestException.error("Student not found");
    }


    @Transactional
    public StudentDto create(@RequestBody StudentDto studentDto) {

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
                .phoneNumber(studentDto.getPhoneNumber())
                .user(build)
                .build();

        userRepository.save(build);
        studentRepository.save(result);
        return studentDto;
    }

    @Transactional
    public StudentDto update(@PathVariable Long id, @RequestBody StudentDto studentDto) {
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
                student.setPhoneNumber(studentDto.getPhoneNumber());
                userRepository.save(byUsername);
                studentRepository.save(student);
                return studentDto;
            }
            throw RestException.error("Student not found");
        }

        throw RestException.error("Student not found");
    }

    @Transactional
    public StudentDto delete(@PathVariable Long id) {

        Optional<Student> byId = studentRepository.findById(id);
        if (byId.isPresent()) {
            Student student = byId.get();
            userRepository.delete(student.getUser());
            studentRepository.delete(student);


            StudentDto result = StudentDto.builder()
                    .id(student.getId())
                    .firstName(student.getFirstName())
                    .lastName(student.getLastName())
                    .gender(student.getGender())
                    .username(student.getUser().getUsername())
                    .email(student.getUser().getEmail())
                    .role(student.getUser().getRoleEnum())
                    .phoneNumber(student.getPhoneNumber())
                    .build();
            return result;
        }
        throw RestException.error("Student not found");
    }


}
