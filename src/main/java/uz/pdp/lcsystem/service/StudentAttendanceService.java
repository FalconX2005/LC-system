package uz.pdp.lcsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.lcsystem.entity.Group;
import uz.pdp.lcsystem.entity.Student;
import uz.pdp.lcsystem.entity.attendences.StudentAttendance;
import uz.pdp.lcsystem.exception.RestException;
import uz.pdp.lcsystem.payload.StudentAttendanceDTO;
import uz.pdp.lcsystem.payload.StudentDto;
import uz.pdp.lcsystem.repository.GroupRepository;
import uz.pdp.lcsystem.repository.StudentAttendanceRepository;
import uz.pdp.lcsystem.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentAttendanceService {
    private final StudentAttendanceRepository studentAttendanceRepository;
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    public List<StudentAttendanceDTO> getAttendances() {

        List<StudentAttendance> attendances = studentAttendanceRepository.findAll();

        List<StudentAttendanceDTO> resultList = new ArrayList<>();

        for (StudentAttendance attendance : attendances) {
            StudentAttendanceDTO build = StudentAttendanceDTO.builder()
                    .attendanceDate(attendance.getAttendanceDate())
                    .groupId(attendance.getGroup().getId())
                    .studentName(attendance.getStudent().getFirstName())
                    .studentId(attendance.getStudent().getId())
                    .status(attendance.isStatus())
                    .build();
            resultList.add(build);
        }
        if (attendances.size() > 0) {
            return resultList;
        }
        throw RestException.error("List of attendances is empty");
    }


    public List<StudentAttendanceDTO> getAttendancesByGroup(Long groupId) {
        List<StudentAttendance> byGroupId = studentAttendanceRepository.findByGroupId(groupId);
        List<StudentAttendanceDTO> resultList = new ArrayList<>();

        for (StudentAttendance attendance : byGroupId) {
            StudentAttendanceDTO build = StudentAttendanceDTO.builder()
                    .attendanceDate(attendance.getAttendanceDate())
                    .groupId(attendance.getGroup().getId())
                    .studentName(attendance.getStudent().getFirstName())
                    .studentId(attendance.getStudent().getId())
                    .status(attendance.isStatus())
                    .build();
            resultList.add(build);
        }
        return resultList;

    }

    public List<StudentAttendanceDTO> create (List<StudentAttendanceDTO> attendances) {
        for (StudentAttendanceDTO attendance : attendances) {

            StudentAttendance build = StudentAttendance.builder()
                    .attendanceDate(attendance.getAttendanceDate())
                    .status(attendance.getStatus())
                    .build();

            Optional<Group> byId = groupRepository.findById(attendance.getGroupId());
            if (byId.isPresent()) {
                Group group = byId.get();
                build.setGroup(group);
            }
            else{
                throw RestException.notFound("Group not found",attendance.getGroupId());
            }
            Optional<Student> byStudent = studentRepository.findById(attendance.getStudentId());
            if (byStudent.isPresent()) {
                Student student = byStudent.get();
                build.setStudent(student);
            }else{
                throw RestException.notFound("Student not found", attendance.getStudentId());
            }
            studentAttendanceRepository.save(build);

        }
        return attendances;
    }
}
