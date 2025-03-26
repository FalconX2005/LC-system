package uz.pdp.lcsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lcsystem.payload.ApiResult;
import uz.pdp.lcsystem.payload.GroupStudentsDTO;
import uz.pdp.lcsystem.service.GroupStudentsService;

import java.util.List;

@RestController
@RequestMapping("/get")
@RequiredArgsConstructor
public class GroupStudentsController {

    private final GroupStudentsService groupStudentsService;

    @GetMapping
    public ApiResult<List<GroupStudentsDTO>> get() {
        List<GroupStudentsDTO> all = groupStudentsService.getAll();
        return ApiResult.success(all);
    }

    @GetMapping("/{id}")
    public ApiResult<GroupStudentsDTO> getById(@PathVariable Long id) {
        GroupStudentsDTO byId = groupStudentsService.getById(id);
        return ApiResult.success(byId);
    }

    // studentni guruhga biriktirish uchun yozilgan method
    @PostMapping("/assing-student")
    public ApiResult<GroupStudentsDTO> assingStudent(@RequestBody GroupStudentsDTO groupStudents) {
        GroupStudentsDTO groupStudentsDto = groupStudentsService.assignStudentToGroup(groupStudents);
        return ApiResult.success(groupStudentsDto);
    }

    @PutMapping("/update/{id}")
    public ApiResult<GroupStudentsDTO> update(@PathVariable Long id,
                                              @RequestBody GroupStudentsDTO groupStudents) {
        GroupStudentsDTO update = groupStudentsService.update(groupStudents);
        return ApiResult.success(update);
    }

    @DeleteMapping("/{id}")
    public ApiResult<GroupStudentsDTO> delete(@PathVariable Long id) {
        GroupStudentsDTO delete = groupStudentsService.delete(id);
        return ApiResult.success(delete);
    }


}
