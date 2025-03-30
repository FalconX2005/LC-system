package uz.pdp.lcsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.lcsystem.entity.Employee;
import uz.pdp.lcsystem.entity.EmployeeGroup;
import uz.pdp.lcsystem.entity.Group;
import uz.pdp.lcsystem.enums.RoleEnum;
import uz.pdp.lcsystem.exception.RestException;
import uz.pdp.lcsystem.payload.EmployeeGroupDTO;
import uz.pdp.lcsystem.payload.withoutId.EmployeeGroupDto;
import uz.pdp.lcsystem.repository.EmployeeGroupRepository;
import uz.pdp.lcsystem.repository.EmployeeRepository;
import uz.pdp.lcsystem.repository.GroupRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeGroupService {
    private final EmployeeGroupRepository employeeGroupRepository;
    private final EmployeeRepository employeeRepository;
    private final GroupRepository groupRepository;

    public List<EmployeeGroupDTO> getAll() {
        List<EmployeeGroup> all = employeeGroupRepository.findAll();
        List<EmployeeGroupDTO> result = new ArrayList<>();
        for (EmployeeGroup employeeGroup : all) {
            if(!employeeGroup.isDeleted()) {
                EmployeeGroupDTO dto = EmployeeGroupDTO.builder()
                        .id(employeeGroup.getId())
                        .employeeId(employeeGroup.getEmployee().getId())
                        .groupId(employeeGroup.getGroup().getId())
                        .build();
                result.add(dto);
            }
        }
        if (!result.isEmpty()) {
            return result;
        }
        throw RestException.error("Employee or group not found");
    }

    public EmployeeGroupDTO getById(Long id) {
        EmployeeGroup employeeGroup = employeeGroupRepository.findById(id)
                .orElseThrow(() -> RestException.error("Employee or group not found"));
        if(!employeeGroup.isDeleted()) {
            return EmployeeGroupDTO.builder()
                    .id(employeeGroup.getId())
                    .employeeId(employeeGroup.getEmployee().getId())
                    .groupId(employeeGroup.getGroup().getId())
                    .build();
        }

        throw RestException.error("Employee or group not found");
    }

    public EmployeeGroupDTO assignEmployeeToGroup(EmployeeGroupDto employeeGroupDto) {
        EmployeeGroup employeeGroup = new EmployeeGroup();
        Group group = groupRepository.findById(employeeGroupDto.getGroupId())
                .orElseThrow(() -> RestException.error("Group not found"));
        Employee employee = employeeRepository.findById(employeeGroupDto.getEmployeeId())
                .orElseThrow(() -> RestException.error("Employee not found"));

        if(!employee.isDeleted()) {

            if(employee.getUser().getRoleEnum() != RoleEnum.TEACHER){
                throw RestException.error("User is not teacher");
            }

            employeeGroup.setGroup(group);
            employeeGroup.setEmployee(employee);
            EmployeeGroup save = employeeGroupRepository.save(employeeGroup);
            EmployeeGroupDTO build = EmployeeGroupDTO.builder()
                    .employeeId(employeeGroupDto.getEmployeeId())
                    .groupId(employeeGroupDto.getGroupId())
                    .id(save.getId())
                    .build();
            return build;
        }

        throw RestException.error("Teacher was deleted");
    }

    public EmployeeGroupDTO update(Long id,EmployeeGroupDto employeeGroupDto) {
        EmployeeGroup employeeGroup = employeeGroupRepository.findById(id)
                .orElseThrow(() -> RestException.error("Employee or group not found"));

        EmployeeGroupDTO employeeGroupDTO = new EmployeeGroupDTO();

        if(!employeeGroup.isDeleted()) {
            
            groupRepository.findById(employeeGroupDto.getGroupId()).ifPresent(employeeGroup::setGroup);
            Optional<Employee> employeeOpt = employeeRepository.findById(employeeGroupDto.getEmployeeId());
            employeeOpt.ifPresent(employeeGroup::setEmployee);

            Employee employee = employeeOpt.get();

            if(employee.getUser().getRoleEnum() != RoleEnum.TEACHER){
                throw RestException.error("User is not teacher");
            }

            EmployeeGroup save = employeeGroupRepository.save(employeeGroup);
            employeeGroup.setId(save.getId());
            employeeGroupDTO.setId(employeeGroup.getId());
            employeeGroupDTO.setEmployeeId(employeeGroup.getEmployee().getId());
            employeeGroupDTO.setGroupId(employeeGroup.getGroup().getId());
            return employeeGroupDTO;
        }

        throw RestException.error("Employee or group not found");
    }

    public EmployeeGroupDTO delete(Long id) {
        EmployeeGroup employeeGroup = employeeGroupRepository.findById(id)
                .orElseThrow(() -> RestException.error("Object does not deleted by id"));

        employeeGroupRepository.delete(employeeGroup);
        return EmployeeGroupDTO.builder()
                .id(id)
                .groupId(employeeGroup.getGroup().getId())
                .employeeId(employeeGroup.getEmployee().getId())
                .build();
    }
}

