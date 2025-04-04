package uz.pdp.lcsystem.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.lcsystem.entity.Attachment;
import uz.pdp.lcsystem.entity.Employee;
import uz.pdp.lcsystem.entity.User;
import uz.pdp.lcsystem.exception.RestException;
import uz.pdp.lcsystem.payload.ApiResult;
import uz.pdp.lcsystem.payload.EmployeeDTO;
import uz.pdp.lcsystem.payload.UserDTO;
import uz.pdp.lcsystem.payload.withoutId.EmployeeDto;
import uz.pdp.lcsystem.repository.AttachmentRepository;
import uz.pdp.lcsystem.repository.EmployeeRepository;
import uz.pdp.lcsystem.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by: Umar
 * DateTime: 3/8/2025 9:52 PM
 */
@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final PasswordEncoder passwordEncoder;
    private final EmployeeRepository employeeRepository;
    private final AttachmentRepository attachmentRepository;
    private final UserRepository userRepository;


    public ApiResult<List<EmployeeDTO>> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> empDTOs = new ArrayList<>();
        for (Employee employee : employees) {
            if (!employee.isDeleted()) {
                UserDTO userDTO = UserDTO.builder().
                        roleEnum(employee.getUser().getRoleEnum()).
                        build();

                EmployeeDTO empDTO = EmployeeDTO.builder().
                        id(employee.getId()).
                        firstName(employee.getFirstName()).
                        lastName(employee.getLastName()).
                        phoneNumber(employee.getPhoneNumber()).
                        user(userDTO).
                        build();
                empDTOs.add(empDTO);
            }
        }


        return ApiResult.success(empDTOs);
    }


    public ApiResult<EmployeeDTO> findById(Long id) {
        Optional<Employee> employeeId = employeeRepository.findById(id);

        if (employeeId.isPresent() ) {

            Employee employee = employeeId.get();
            Optional<User> byId = userRepository.findById(employee.getUser().getId());
            if(byId.isPresent()) {
                User user = byId.get();
                UserDTO userDTO = UserDTO.builder().
                        roleEnum(user.getRoleEnum()).
                        email(user.getEmail()).
                        username(user.getUsername()).
                        build();


                if (!employee.isDeleted()) {
                    EmployeeDTO employeeDTO = EmployeeDTO.builder().
                            id(employee.getId()).
                            firstName(employee.getFirstName()).
                            lastName(employee.getLastName()).
                            gender(employee.getGender()).
                            phoneNumber(employee.getPhoneNumber()).
                            birthDate(employee.getBirthDate()).
                            salary(employee.getSalary()).
                            attachmentId(employee.getAttachment().getId()).
                            user(userDTO).
                            build();
                    return ApiResult.success(employeeDTO);
                }
            }
        }

        throw RestException.notFound("Employee not found", id);
    }

    @Transactional
    public ApiResult<EmployeeDTO> create(EmployeeDto employeeDTO) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setGender(employeeDTO.getGender());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setBirthDate(employeeDTO.getBirthDate());
        employee.setSalary(employeeDTO.getSalary());

        User user = new User();
        user.setEmail(employeeDTO.getUser().getEmail());
        user.setPassword(passwordEncoder.encode(employeeDTO.getUser().getPassword()));
        user.setUsername(employeeDTO.getUser().getUsername());
        user.setRoleEnum(employeeDTO.getUser().getRoleEnum());
        User save = userRepository.save(user);
        employee.setUser(user);

        if (employeeDTO.getAttachmentId() != null) {
            Attachment attachmentId = attachmentRepository.findById(employeeDTO.getAttachmentId())
                    .orElseThrow(() -> RestException.notFound("Attachment not found: ", employeeDTO.getAttachmentId()));

            employee.setAttachment(attachmentId);
        }

        employeeRepository.save(employee);

        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .roleEnum(user.getRoleEnum())
                .build();


        EmployeeDTO empDTO = EmployeeDTO.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .gender(employee.getGender())
                .salary(employee.getSalary())
                .phoneNumber(employee.getPhoneNumber())
                .birthDate(employee.getBirthDate())
                .attachmentId(employee.getAttachment() != null ? employee.getAttachment().getId() : null)
                .user(userDTO)
                .build();

        return ApiResult.success(empDTO);
    }


    @Transactional
    public ApiResult<EmployeeDTO> update(Long id, EmployeeDto employeeDTO) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> RestException.notFound("Employee not found", id));

        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setGender(employeeDTO.getGender());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setBirthDate(employeeDTO.getBirthDate());
        employee.setSalary(employeeDTO.getSalary());
        User user = new User();
        user.setEmail(employeeDTO.getUser().getEmail());
        user.setPassword(passwordEncoder.encode(employeeDTO.getUser().getPassword()));        user.setUsername(employeeDTO.getUser().getUsername());
        user.setRoleEnum(employeeDTO.getUser().getRoleEnum());
        User save = userRepository.save(user);

        if (employeeDTO.getAttachmentId() != null) {
            Attachment attachmentId = attachmentRepository.findById(employeeDTO.getAttachmentId())
                    .orElseThrow(() -> RestException.notFound("Attachment not found: ", employeeDTO.getAttachmentId()));

            employee.setAttachment(attachmentId);
        }

        employee = employeeRepository.save(employee);


        UserDTO userDTO = UserDTO.builder()
                .id(save.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .roleEnum(user.getRoleEnum())
                .build();

        if (!employee.isDeleted()) {
            EmployeeDTO buildDTO = EmployeeDTO.builder()
                    .id(employee.getId())
                    .firstName(employee.getFirstName())
                    .lastName(employee.getLastName())
                    .gender(employee.getGender())
                    .salary(employee.getSalary())
                    .phoneNumber(employee.getPhoneNumber())
                    .birthDate(employee.getBirthDate())
                    .attachmentId(employee.getAttachment() != null ? employee.getAttachment().getId() : null)
                    .user(userDTO)
                    .build();
            return ApiResult.success(buildDTO);
        }
        throw RestException.notFound("Employee not found", employee.getId());
    }


    public ApiResult<EmployeeDTO> delete(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw RestException.notFound("Employee not found", id);
        }
        employeeRepository.deleteById(id);
        return ApiResult.success("Employee deleted successfully");
    }

}
