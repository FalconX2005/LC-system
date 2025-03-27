package uz.pdp.lcsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.lcsystem.entity.Group;
import uz.pdp.lcsystem.entity.GroupStudents;
import uz.pdp.lcsystem.entity.Student;
import uz.pdp.lcsystem.exception.RestException;
import uz.pdp.lcsystem.payload.GroupStudentsDTO;
import uz.pdp.lcsystem.repository.GroupRepository;
import uz.pdp.lcsystem.repository.GroupStudentsRepository;
import uz.pdp.lcsystem.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupStudentsService {
    private final GroupStudentsRepository groupStudentsRepository;
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    public List<GroupStudentsDTO> getAll(){
        List<GroupStudents> all = groupStudentsRepository.findAll();
        List<GroupStudentsDTO> result = new ArrayList<>();
        for (GroupStudents groupStudents : all) {
            GroupStudentsDTO build = GroupStudentsDTO.builder()
                    .id(groupStudents.getId())
                    .groupId(groupStudents.getGroup().getId())
                    .studentId(groupStudents.getStudent().getId())
                    .build();
            result.add(build);
        }
        if (result.size() > 0) {
            return result;
        }

        throw RestException.error("Group or student not found");
    }
    public GroupStudentsDTO getById(Long id) {
        Optional<GroupStudents> byId = groupStudentsRepository.findById(id);
        if (byId.isPresent()) {
            GroupStudentsDTO result = GroupStudentsDTO.builder()
                    .id(byId.get().getId())
                    .groupId(byId.get().getGroup().getId())
                    .studentId(byId.get().getStudent().getId())
                    .build();
            return result;
        }
        throw RestException.error("Group or student not found");
    }
    public GroupStudentsDTO assignStudentToGroup(GroupStudentsDTO groupStudentsDto) {
        GroupStudents groupStudents = new GroupStudents();
        Optional<Group> byId = groupRepository.findById(groupStudentsDto.getGroupId());
        if (byId.isPresent()) {
            Group group = byId.get();
            groupStudents.setGroup(group);
        }else {
            throw RestException.error("Group not found");
        }
        Optional<Student> byStudentId = studentRepository.findById(groupStudentsDto.getStudentId());
        if (byStudentId.isPresent()) {
            Student student = byStudentId.get();
            groupStudents.setStudent(student);
            GroupStudents save = groupStudentsRepository.save(groupStudents);
            groupStudentsDto.setId(save.getId());
        }else {
            throw RestException.error("Student not found");
        }
        return groupStudentsDto;
    }

    public GroupStudentsDTO update (GroupStudentsDTO groupStudentsDto) {
        Optional<GroupStudents> byId = groupStudentsRepository.findById(groupStudentsDto.getId());
        if (byId.isPresent()) {
            GroupStudents groupStudents = byId.get();
            groupRepository.findById(groupStudentsDto.getGroupId()).ifPresent(group -> {groupStudents.setGroup(group);});
            studentRepository.findById(groupStudentsDto.getStudentId()).ifPresent(student -> {groupStudents.setStudent(student);});
            GroupStudents save = groupStudentsRepository.save(groupStudents);
            groupStudentsDto.setId(save.getId());
            return groupStudentsDto;
        }
        throw RestException.error("Group not found");
    }

    public GroupStudentsDTO delete(Long id ){
        Optional<GroupStudents> byId = groupStudentsRepository.findById(id);
        if (byId.isPresent()) {
            GroupStudents groupStudents = byId.get();
            groupStudentsRepository.delete(groupStudents);
            return GroupStudentsDTO.builder()
                    .id(id)
                    .groupId(groupStudents.getGroup().getId())
                    .studentId(groupStudents.getStudent().getId())
                    .build();
        }
        throw RestException.error("Object does not deleted by id");

    }


}
