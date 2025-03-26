package uz.pdp.lcsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.lcsystem.entity.Employee;
import uz.pdp.lcsystem.entity.Group;
import uz.pdp.lcsystem.entity.attendences.TeacherAttendance;
import uz.pdp.lcsystem.exception.RestException;
import uz.pdp.lcsystem.payload.ApiResult;
import uz.pdp.lcsystem.payload.StudentAttendanceDTO;
import uz.pdp.lcsystem.payload.TeacherAttendanceDTO;
import uz.pdp.lcsystem.repository.EmployeeRepository;
import uz.pdp.lcsystem.repository.GroupRepository;
import uz.pdp.lcsystem.repository.TeacherAttendanceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by: Umar
 * DateTime: 3/19/2025 2:49 PM
 */

@Service
@RequiredArgsConstructor
public class TeacherAttendanceService {
    private final TeacherAttendanceRepository teacherAttendanceRepository;
    private final EmployeeRepository employeeRepository;
    private final GroupRepository groupRepository;


    public List<TeacherAttendanceDTO> getTeacherAttendance() {

        List<TeacherAttendance> teacherAttendanceList = teacherAttendanceRepository.findAll();

        List<TeacherAttendanceDTO> resList = new ArrayList<>();

        for (TeacherAttendance teacherAttendance : teacherAttendanceList) {
            TeacherAttendanceDTO attendanceDTO = TeacherAttendanceDTO.builder().
                    attendanceDate(teacherAttendance.getAttendanceDate()).
                    groupId(teacherAttendance.getGroup().getId()).
                    teacherName(teacherAttendance.getEmployee().getFirstName()).
                    teacherId(teacherAttendance.getEmployee().getId()).
                    status(teacherAttendance.isStatus()).
                    build();
            resList.add(attendanceDTO);
        }

        if(!teacherAttendanceList.isEmpty()) {
            return resList;
        }
        throw RestException.error("List of attendance is empty");
    }

    
    public List<TeacherAttendanceDTO> getTeacherAttendanceByGroupId(Long groupId) {
        List<TeacherAttendance> byGroupId = teacherAttendanceRepository.findByGroupId(groupId);

        List<TeacherAttendanceDTO> resList = new ArrayList<>();
        for (TeacherAttendance teacherAttendance : byGroupId) {
            TeacherAttendanceDTO attendanceDTO = TeacherAttendanceDTO.builder().
                    attendanceDate(teacherAttendance.getAttendanceDate()).
                    groupId(teacherAttendance.getGroup().getId()).
                    teacherName(teacherAttendance.getEmployee().getFirstName()).
                    teacherId(teacherAttendance.getEmployee().getId()).
                    status(teacherAttendance.isStatus()).
                    build();
            resList.add(attendanceDTO);
        }

        if(!byGroupId.isEmpty()) {
            return resList;
        }

        throw RestException.error("List of attendance is empty");

    }

    public List<TeacherAttendanceDTO> createTeacherAttendance(List<TeacherAttendanceDTO> attendanceDTO) {

        for (TeacherAttendanceDTO teacherAttendanceDTO : attendanceDTO) {

            TeacherAttendance teacherAttendance = TeacherAttendance.builder().
                    attendanceDate(teacherAttendanceDTO.getAttendanceDate()).
                    status(teacherAttendanceDTO.isStatus()).
                    build();


            Optional<Group> byId = groupRepository.findById(teacherAttendanceDTO.getGroupId());
            if(byId.isPresent()){
                Group group = byId.get();
                teacherAttendance.setGroup(group);
            }else {
                throw RestException.notFound("Group is not found ", teacherAttendanceDTO.getGroupId());
            }

            Optional<Employee> teacher = employeeRepository.findById(teacherAttendanceDTO.getTeacherId());
            if(teacher.isPresent()){
                Employee employee = teacher.get();
                teacherAttendance.setEmployee(employee);
            }else {
                throw RestException.notFound("Teacher is not found ", teacherAttendanceDTO.getTeacherId());
            }

            teacherAttendanceRepository.save(teacherAttendance);
        }

        return attendanceDTO;
    }

}
