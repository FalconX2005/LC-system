package uz.pdp.lcsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.lcsystem.entity.Employee;
import uz.pdp.lcsystem.entity.EmployeeGroup;
import uz.pdp.lcsystem.entity.Group;
import uz.pdp.lcsystem.exception.RestException;
import uz.pdp.lcsystem.payload.EmployeeGroupDTO;
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
            EmployeeGroupDTO dto = EmployeeGroupDTO.builder()
                    .id(employeeGroup.getId())
                    .employeeId(employeeGroup.getEmployee().getId())
                    .groupId(employeeGroup.getGroup().getId())
                    .build();
            result.add(dto);
        }
        if (!result.isEmpty()) {
            return result;
        }
        throw RestException.error("Employee or group not found");
    }

    public EmployeeGroupDTO getById(Long id) {
        EmployeeGroup employeeGroup = employeeGroupRepository.findById(id)
                .orElseThrow(() -> RestException.error("Employee or group not found"));
        return EmployeeGroupDTO.builder()
                .id(employeeGroup.getId())
                .employeeId(employeeGroup.getEmployee().getId())
                .groupId(employeeGroup.getGroup().getId())
                .build();
    }

    public EmployeeGroupDTO assignEmployeeToGroup(EmployeeGroupDTO employeeGroupDto) {
        EmployeeGroup employeeGroup = new EmployeeGroup();
        Group group = groupRepository.findById(employeeGroupDto.getGroupId())
                .orElseThrow(() -> RestException.error("Group not found"));
        Employee employee = employeeRepository.findById(employeeGroupDto.getEmployeeId())
                .orElseThrow(() -> RestException.error("Employee not found"));

        employeeGroup.setGroup(group);
        employeeGroup.setEmployee(employee);
        EmployeeGroup save = employeeGroupRepository.save(employeeGroup);
        employeeGroupDto.setId(save.getId());
        return employeeGroupDto;
    }

    public EmployeeGroupDTO update(EmployeeGroupDTO employeeGroupDto) {
        EmployeeGroup employeeGroup = employeeGroupRepository.findById(employeeGroupDto.getId())
                .orElseThrow(() -> RestException.error("Employee or group not found"));

        groupRepository.findById(employeeGroupDto.getGroupId()).ifPresent(employeeGroup::setGroup);
        employeeRepository.findById(employeeGroupDto.getEmployeeId()).ifPresent(employeeGroup::setEmployee);

        EmployeeGroup save = employeeGroupRepository.save(employeeGroup);
        employeeGroupDto.setId(save.getId());
        return employeeGroupDto;
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

