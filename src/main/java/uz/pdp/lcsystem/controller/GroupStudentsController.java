package uz.pdp.lcsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lcsystem.payload.ApiResult;
import uz.pdp.lcsystem.payload.GroupStudentsDto;
import uz.pdp.lcsystem.service.GroupStudentsService;

import java.util.List;

@RestController
@RequestMapping("/get")
@RequiredArgsConstructor
public class GroupStudentsController {
    private final GroupStudentsService groupStudentsService;

    @GetMapping
    public ApiResult<List<GroupStudentsDto>> get() {
        List<GroupStudentsDto> all = groupStudentsService.getAll();
        return ApiResult.success(all);
    }

    @GetMapping("/{id}")
    public ApiResult<GroupStudentsDto> getById(@PathVariable Long id) {
        GroupStudentsDto byId = groupStudentsService.getById(id);
        return ApiResult.success(byId);
    }

    // studentni guruhga biriktirish uchun yozilgan method
    @PostMapping("/assing-student")
    public ApiResult<GroupStudentsDto> assingStudent(@RequestBody GroupStudentsDto groupStudents) {
        GroupStudentsDto groupStudentsDto = groupStudentsService.assignStudentToGroup(groupStudents);
        return ApiResult.success(groupStudentsDto);
    }

    @PutMapping("/update/{id}")
    public ApiResult<GroupStudentsDto> update(@PathVariable Long id,
                                              @RequestBody GroupStudentsDto groupStudents) {
        GroupStudentsDto update = groupStudentsService.update(groupStudents);
        return ApiResult.success(update);
    }

    @DeleteMapping("/{id}")
    public ApiResult<GroupStudentsDto> delete(@PathVariable Long id) {
        GroupStudentsDto delete = groupStudentsService.delete(id);
        return ApiResult.success(delete);
    }
}
