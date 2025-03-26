package uz.pdp.lcsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lcsystem.entity.EmployeeGroup;
import uz.pdp.lcsystem.payload.ApiResult;
import uz.pdp.lcsystem.payload.EmployeeGroupDTO;
import uz.pdp.lcsystem.service.EmployeeGroupService;

import java.util.List;

/**
 * Created by: Umar
 * DateTime: 3/26/2025 1:29 PM
 */
@RestController
@RequestMapping("/empgroup")
@RequiredArgsConstructor
public class EmployeeGroupController {

    private final EmployeeGroupService employeeGroupService;

    @GetMapping("/getall")
    public ApiResult<List<EmployeeGroupDTO>> getAll() {
        List<EmployeeGroupDTO> employeeGroups = employeeGroupService.getAll();
        return ApiResult.success(employeeGroups);
    }


    @GetMapping("/{id}")
    public ApiResult<EmployeeGroupDTO> getById(@PathVariable Long id) {
        EmployeeGroupDTO employeeGroup = employeeGroupService.getById(id);
        return ApiResult.success(employeeGroup);
    }


    @PostMapping("/assign-employee")
    public ApiResult<EmployeeGroupDTO> connectEmployee(@RequestBody EmployeeGroupDTO employeeGroupDTO) {
        EmployeeGroupDTO groupDTO = employeeGroupService.assignEmployeeToGroup(employeeGroupDTO);
        return ApiResult.success(groupDTO);
    }

    @PutMapping("/update/{id}")
    public ApiResult<EmployeeGroupDTO> updateEmployee(@PathVariable Long id,
                                                      @RequestBody EmployeeGroupDTO employeeGroupDTO) {

        EmployeeGroupDTO groupDTO = employeeGroupService.update(employeeGroupDTO);
        return ApiResult.success(groupDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResult<EmployeeGroupDTO> delete(@PathVariable Long id) {
        EmployeeGroupDTO groupDTO = employeeGroupService.delete(id);
        return ApiResult.success(groupDTO);
    }

}
