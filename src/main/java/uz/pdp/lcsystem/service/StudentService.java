package uz.pdp.lcsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.lcsystem.entity.Student;
import uz.pdp.lcsystem.entity.User;
import uz.pdp.lcsystem.exception.RestException;
import uz.pdp.lcsystem.payload.StudentDTO;
import uz.pdp.lcsystem.payload.withoutId.StudentDto;
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


    private final StudentRepository studentRepository;

    private final StudentAttendanceRepository studentAttendanceRepository;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public List<StudentDTO> getAll() {
        List<Student> students = studentRepository.findAll();

        List<StudentDTO> result = new ArrayList<>();

        for (Student student : students) {

            if(!student.isDeleted()) {
                StudentDTO build = StudentDTO.builder()
                        .id(student.getId()).
                        firstName(student.getFirstName())
                        .lastName(student.getLastName())
                        .username(student.getUser().getUsername())
                        .email(student.getUser().getEmail())
                        .role(student.getUser().getRoleEnum())
                        .gender(student.getGender())
                        .phoneNumber(student.getPhoneNumber())
                        .build();
                result.add(build);
            }

        }

        if(result.isEmpty()) {
            throw RestException.error("List is empty");
        }else {
            return result;
        }
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
                    .phoneNumber(student.getPhoneNumber())
                    .build();
            return build;
        }
        throw RestException.error("Student not found");
    }


    @Transactional
    public StudentDTO create(@RequestBody StudentDto studentDto) {

        User build = User.builder()
                .username(studentDto.getUsername())
                .password(passwordEncoder.encode(studentDto.getPassword()))
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
        Student save = studentRepository.save(result);
        StudentDTO build1 = StudentDTO.builder()
                .id(save.getId())
                .firstName(save.getFirstName())
                .lastName(save.getLastName())
                .gender(save.getGender())
                .phoneNumber(save.getPhoneNumber())
                .email(save.getUser().getEmail())
                .username(save.getUser().getUsername())
                .build();
        return build1;
    }

    @Transactional
    public StudentDTO update(@PathVariable Long id, @RequestBody StudentDto studentDto) {
        Optional<User> findByUsername = userRepository.findByUsername(studentDto.getUsername());

        StudentDTO studentDTO = new StudentDTO();

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
                Student save = studentRepository.save(student);


                studentDTO.setId(save.getId());
                studentDTO.setFirstName(save.getFirstName());
                studentDTO.setLastName(save.getLastName());
                studentDTO.setUsername(save.getUser().getUsername());
                studentDTO.setPassword(passwordEncoder.encode(save.getUser().getPassword()));
                studentDTO.setRole(studentDto.getRole());
                studentDTO.setGender(studentDto.getGender());
                studentDTO.setEmail(studentDto.getEmail());
                studentDTO.setPhoneNumber(studentDto.getPhoneNumber());
                return studentDTO;
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
                    .phoneNumber(student.getPhoneNumber())
                    .build();
            return result;
        }
        throw RestException.error("Student not found");
    }


}
