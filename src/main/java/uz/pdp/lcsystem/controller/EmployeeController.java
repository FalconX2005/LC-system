package uz.pdp.lcsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.lcsystem.payload.ApiResult;
import uz.pdp.lcsystem.payload.EmployeeDTO;
import uz.pdp.lcsystem.service.EmployeeService;
import uz.pdp.lcsystem.service.SearchService;

import java.util.List;

/**
 * Created by: Umar
 * DateTime: 3/8/2025 11:27 PM
 */
@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/read")
    public ApiResult<List<EmployeeDTO>> getAll() {
        return employeeService.findAll();
    }


    @GetMapping("/read/{id}")
    public ApiResult<EmployeeDTO> getEmployee(@PathVariable Long id) {
        return employeeService.findById(id);
    }


    @PostMapping("/create")
    public ApiResult<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.create(employeeDTO);
    }

    private final SearchService searchService;

    @PutMapping("/update/{id}")
    public ApiResult<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.update(employeeDTO);
    }

    @GetMapping("/search")
    public ApiResult<List<EmployeeDTO>> searchEmployees(@RequestParam String name) {
        List<EmployeeDTO> result = searchService.searchEmployee(name);
        return ApiResult.success(result);
    }


    @DeleteMapping("/delete/{id}")
    public ApiResult<EmployeeDTO> deleteEmployee(@PathVariable Long id) {
        return employeeService.delete(id);
    }

}