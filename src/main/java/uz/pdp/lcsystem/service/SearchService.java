package uz.pdp.lcsystem.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.lcsystem.entity.*;
import uz.pdp.lcsystem.entity.attendences.TeacherAttendance;
import uz.pdp.lcsystem.exception.RestException;
import uz.pdp.lcsystem.payload.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
public class SearchService {

    private final EntityManager entityManager;

    public List<StudentDto> searchStudent(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = cb.createQuery(Student.class);
        Root<Student> root = criteriaQuery.from(Student.class);
        List<Predicate> predicates = new ArrayList<>();

        Predicate firstNamePredicate = cb.like(cb.lower(root.get("firstName")), "%" + name.toLowerCase() + "%");

        Predicate lastNamePredicate = cb.like(cb.lower(root.get("lastName")), "%" + name.toLowerCase() + "%");

        Predicate searchPredicate = cb.or(firstNamePredicate, lastNamePredicate);
        predicates.add(searchPredicate);

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        List<Student> students = entityManager.createQuery(criteriaQuery).getResultList();
        if (students.isEmpty()) {
            throw RestException.error("Student not found");
        }

        return students.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private StudentDto convertToDto(Student student) {
        return StudentDto.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getUser().getEmail())
                .gender(student.getGender())
                .role(student.getUser().getRoleEnum())
                .username(student.getUser().getUsername())
                .build();
    }

    /// /////////////////////////////
    public List<GroupDTO> searchGroup(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Group> criteriaQuery = cb.createQuery(Group.class);
        Root<Group> root = criteriaQuery.from(Group.class);
        List<Predicate> predicates = new ArrayList<>();

        Path<String> groupNamePath = root.get("groupName");
        Predicate groupNamePredicate = cb.like(cb.lower(groupNamePath), "%" + name.toLowerCase() + "%");
        predicates.add(groupNamePredicate);

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        List<Group> groups = entityManager.createQuery(criteriaQuery).getResultList();

        if (groups.isEmpty()) {
            throw RestException.error("Group not found");
        }

        return groups.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private GroupDTO convertToDto(Group group) {
        return GroupDTO.builder()
                .id(group.getId())
                .name(group.getGroupName())
                .courseId(group.getCourse() != null ? group.getCourse().getId() : null)
                .employeeId(group.getGroups() != null && !group.getGroups().isEmpty()
                        ? group.getGroups().get(0).getEmployee().getId()
                        : null)
                .roomId(group.getRoom() != null ? group.getRoom().getId() : null)
                .stNumber(group.getStNumber())
                .startTime(group.getStartTime())
                .endTime(group.getEndTime())
                .startDate(group.getStartDate())
                .endDate(group.getEndDate())
                .status(group.getStatus())
                .build();
    }
    /// //////////////////////////
    public List<EmployeeDTO> searchEmployee(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = cb.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        List<Predicate> predicates = new ArrayList<>();

        Path<String> firstNamePath = root.get("firstName");
        Path<String> lastNamePath = root.get("lastName");

        Predicate firstNamePredicate = cb.like(cb.lower(firstNamePath), "%" + name.toLowerCase() + "%");
        Predicate lastNamePredicate = cb.like(cb.lower(lastNamePath), "%" + name.toLowerCase() + "%");

        predicates.add(cb.or(firstNamePredicate, lastNamePredicate));

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        List<Employee> employees = entityManager.createQuery(criteriaQuery).getResultList();

        if (employees.isEmpty()) {
            throw RestException.error("Employee not found");
        }

        return employees.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private EmployeeDTO convertToDto(Employee employee) {
        return EmployeeDTO.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .birthDate(employee.getBirthDate())
                .phoneNumber(employee.getPhoneNumber())
                .gender(employee.getGender())
                .salary(employee.getSalary())
                .userId((int) employee.getUser().getId().longValue())
                .attendances(employee.getAttendances().stream().map(this::convertAttendanceToDto).collect(Collectors.toList()))
                .build();
    }

    private TeacherAttendanceDTO convertAttendanceToDto(TeacherAttendance attendance) {
        return TeacherAttendanceDTO.builder()
                .id(attendance.getId())
                .attendanceDate(attendance.getAttendanceDate())
                .status(attendance.isStatus())
                .build();
    }
   /// //////////////////////////
    public List<RoomDto> searchRoom(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Room> criteriaQuery = cb.createQuery(Room.class);
        Root<Room> root = criteriaQuery.from(Room.class);
        List<Predicate> predicates = new ArrayList<>();

        Path<String> roomNamePath = root.get("name");
        Predicate roomNamePredicate = cb.like(cb.lower(roomNamePath), "%" + name.toLowerCase() + "%");
        predicates.add(roomNamePredicate);

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        List<Room> rooms = entityManager.createQuery(criteriaQuery).getResultList();

        if (rooms.isEmpty()) {
            throw RestException.error("Room not found");
        }

        return rooms.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private RoomDto convertToDto(Room room) {
        return RoomDto.builder()
                .id(room.getId())
                .name(room.getName())
                .capacity(room.getCapacity())
                .countOfTable(room.getCountOfTable())
                .countOfChair(room.getCountOfChair())
                .build();
    }
    /// ///////////////////////////////
    public List<CourseDTO> searchCourse(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = cb.createQuery(Course.class);
        Root<Course> root = criteriaQuery.from(Course.class);
        List<Predicate> predicates = new ArrayList<>();

        Path<String> courseNamePath = root.get("courseName");
        Predicate courseNamePredicate = cb.like(cb.lower(courseNamePath), "%" + name.toLowerCase() + "%");
        predicates.add(courseNamePredicate);

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        List<Course> courses = entityManager.createQuery(criteriaQuery).getResultList();

        if (courses.isEmpty()) {
            throw RestException.error("Course not found");
        }

        return courses.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private CourseDTO convertToDto(Course course) {
        return CourseDTO.builder()
                .id(course.getId())
                .name(course.getCourseName())
                .price(course.getPrice())
                .build();
    }



}


