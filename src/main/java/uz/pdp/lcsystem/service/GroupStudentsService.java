package uz.pdp.lcsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.lcsystem.entity.Group;
import uz.pdp.lcsystem.entity.GroupStudents;
import uz.pdp.lcsystem.entity.Student;
import uz.pdp.lcsystem.exception.RestException;
import uz.pdp.lcsystem.payload.GroupStudentsDTO;
import uz.pdp.lcsystem.payload.withoutId.GroupStudentsDto;
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

    public List<GroupStudentsDTO> getAll() {
        List<GroupStudents> all = groupStudentsRepository.findAll();
        List<GroupStudentsDTO> result = new ArrayList<>();
        for (GroupStudents groupStudents : all) {
            if (!groupStudents.isDeleted()) {
                GroupStudentsDTO build = GroupStudentsDTO.builder()
                        .id(groupStudents.getId())
                        .groupId(groupStudents.getGroup().getId())
                        .studentId(groupStudents.getStudent().getId())
                        .build();
                result.add(build);
            }
        }
        if (result.size() > 0) {
            return result;
        }

        throw RestException.error("Group or student not found");
    }

    public GroupStudentsDTO getById(Long id) {
        Optional<GroupStudents> byId = groupStudentsRepository.findById(id);
        GroupStudents groupStudents = byId.get();
        if (!groupStudents.isDeleted()) {
            if (byId.isPresent()) {
                GroupStudentsDTO result = GroupStudentsDTO.builder()
                        .id(byId.get().getId())
                        .groupId(groupStudents.getGroup().getId())
                        .studentId(groupStudents.getStudent().getId())
                        .build();
                return result;
            }
        }
        throw RestException.error("Group or student not found");
    }

    public GroupStudentsDTO assignStudentToGroup(GroupStudentsDto groupStudentsDto) {
        GroupStudents groupStudents = new GroupStudents();
        GroupStudentsDTO result = new GroupStudentsDTO();
        Optional<Group> byId = groupRepository.findById(groupStudentsDto.getGroupId());
        if (byId.isPresent()) {
            Group group = byId.get();
            if (!group.isDeleted()) {
                groupStudents.setGroup(group);
                result.setGroupId(group.getId());
            }
            else {
                throw RestException.error("Group or student not found");
            }
        } else {
            throw RestException.error("Group not found");
        }
        Optional<Student> byStudentId = studentRepository.findById(groupStudentsDto.getStudentId());
        if (byStudentId.isPresent()) {
            Student student = byStudentId.get();
            if(!student.isDeleted()) {
                groupStudents.setStudent(student);
                result.setStudentId(student.getId());
                GroupStudents save = groupStudentsRepository.save(groupStudents);
                result.setId(save.getId());

            }else {
                throw RestException.error("Student not found");
            }
        } else {
            throw RestException.error("Student not found");
        }
        return result;
    }

    public GroupStudentsDTO update(Long id,GroupStudentsDto groupStudentsDto) {

        GroupStudentsDTO result = new GroupStudentsDTO();

        Optional<GroupStudents> byId = groupStudentsRepository.findById(id);
        if (byId.isPresent()) {
            GroupStudents groupStudents = byId.get();
            if (!groupStudents.isDeleted()) {
                Optional<Group> group = groupRepository.findById(groupStudentsDto.getGroupId());
                group.ifPresent(groupStudents::setGroup);
                Optional<Student> student = studentRepository.findById(groupStudentsDto.getStudentId());
                student.ifPresent(groupStudents::setStudent);
                groupStudents.setGroup(group.get());
                groupStudents.setStudent(student.get());
                GroupStudents save = groupStudentsRepository.save(groupStudents);
                result.setId(save.getId());
                result.setGroupId(group.get().getId());
                result.setStudentId(student.get().getId());
                return result;
            }
        }
        throw RestException.error("Group not found");
    }

    public GroupStudentsDTO delete(Long id) {
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
