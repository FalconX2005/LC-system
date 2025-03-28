package uz.pdp.lcsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lcsystem.entity.attendences.StudentAttendance;
import uz.pdp.lcsystem.payload.ApiResult;
import uz.pdp.lcsystem.payload.StudentAttendanceDTO;
import uz.pdp.lcsystem.service.StudentAttendanceService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attendance")
public class StudentAttendanceController {


    private final StudentAttendanceService studentAttendanceService;

    @Secured({"ADMIN","MANAGER","TEACHER"})
    //barcha attendance larni korish uchun yozilgan !!!
    @GetMapping
    public ApiResult<List<StudentAttendanceDTO>> getAttendance(){
        List<StudentAttendanceDTO> attendances = studentAttendanceService.getAttendances();

        return ApiResult.success(attendances);
    }

    @Secured({"ADMIN","MANAGER","TEACHER"})
    //bitta groupning ichidagi studentlarini yo'qlama qilish uchun yozilgan !!!
    // Kirib kelgan id bu groupning id si.
    @GetMapping("/{id}")
    public ApiResult<List<StudentAttendanceDTO>> getAttendanceByGroupId(@RequestParam Long groupId){
        List<StudentAttendanceDTO> result = studentAttendanceService.getAttendancesByGroup(groupId);
        return ApiResult.success(result);
    }

    @Secured({"ADMIN","MANAGER","TEACHER"})
    //barcha grouplardagi attendance uchun yozilgan !!!
    @PostMapping("/create")
    public ApiResult<List<StudentAttendanceDTO>> createAttendance(@RequestBody List<StudentAttendanceDTO> studentAttendanceDTOS){
        List<StudentAttendanceDTO> result = studentAttendanceService.create(studentAttendanceDTOS);
        return ApiResult.success(result);
    }
}
