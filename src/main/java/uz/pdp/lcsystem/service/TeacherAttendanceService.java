package uz.pdp.lcsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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


    public ApiResult<List<TeacherAttendanceDTO>> getTeacherAttendance() {

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

        if(teacherAttendanceList.size() > 0){
            return ApiResult.success(resList);
        }
        throw RestException.error("List of attendance is empty");
    }

    
    public ApiResult<List<TeacherAttendanceDTO>> getTeacherAttendanceByGroupId(Long groupId) {
        List<TeacherAttendance> teacher = teacherAttendanceRepository.findByGroupId(groupId);

        

    }

}
