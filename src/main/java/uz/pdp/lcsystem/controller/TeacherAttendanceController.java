package uz.pdp.lcsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lcsystem.payload.ApiResult;
import uz.pdp.lcsystem.payload.StudentAttendanceDTO;
import uz.pdp.lcsystem.payload.TeacherAttendanceDTO;
import uz.pdp.lcsystem.payload.withoutId.TeacherAttendanceDto;
import uz.pdp.lcsystem.service.TeacherAttendanceService;

import java.util.List;

/**
 * Created by: Umar
 * DateTime: 3/24/2025 4:51 PM
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/teacherattendance")
public class TeacherAttendanceController {

    private final TeacherAttendanceService teacherAttendanceService;

    @Secured({"ADMIN","MANAGER"})
    @GetMapping("/all")
    public ApiResult<List<TeacherAttendanceDTO>> getAllTeacherAttendance() {

        List<TeacherAttendanceDTO> attendances = teacherAttendanceService.getTeacherAttendance();
        return ApiResult.success(attendances);
    }

    @Secured({"ADMIN","MANAGER"})
    @GetMapping("/{groupId}")
    public ApiResult<List<TeacherAttendanceDTO>> getTeacherAttendanceByGroupId(@PathVariable Long groupId ) {
        List<TeacherAttendanceDTO> result = teacherAttendanceService.getTeacherAttendanceByGroupId(groupId);
        return ApiResult.success(result);
    }

    @Secured({"ADMIN","MANAGER"})
    @PostMapping("/create")
    public ApiResult<List<TeacherAttendanceDTO>> createTeacherAttendance(@RequestBody List<TeacherAttendanceDto> teacherAttendanceDTOs) {
        List<TeacherAttendanceDTO> teacherAttendance = teacherAttendanceService.createTeacherAttendance(teacherAttendanceDTOs);
        return ApiResult.success(teacherAttendance);
    }
}
