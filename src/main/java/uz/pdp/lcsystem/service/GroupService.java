package uz.pdp.lcsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.lcsystem.entity.Course;
import uz.pdp.lcsystem.entity.Group;
import uz.pdp.lcsystem.entity.Room;
import uz.pdp.lcsystem.enums.Status;
import uz.pdp.lcsystem.exception.RestException;
import uz.pdp.lcsystem.payload.GroupDTO;
import uz.pdp.lcsystem.payload.withoutId.GroupDto;
import uz.pdp.lcsystem.repository.CourseRepository;
import uz.pdp.lcsystem.repository.GroupRepository;
import uz.pdp.lcsystem.repository.RoomRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;
    private final RoomRepository roomRepository;




    public GroupDTO getGroupById(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> RestException.notFound("Group not found", id));
        if (group.isDeleted()) {
            throw RestException.notFound("Group not found", id);
        }
        return convertToDTO(group);
    }
    public List<GroupDTO> getAllGroups() {
        List<Group> groups = groupRepository.findByDeletedFalse();
        return groups.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public GroupDTO createGroup(GroupDto groupDTO) {
        Course course = courseRepository.findById(groupDTO.getCourseId())
                .orElseThrow(() -> RestException.notFound("Course not found", groupDTO.getCourseId()));
        Room room = roomRepository.findById(groupDTO.getRoomId())
                .orElseThrow(() -> RestException.notFound("Room not found", groupDTO.getRoomId()));

        Group group = Group.builder()
                .groupName(groupDTO.getName())
                .course(course)
                .room(room)
                .stNumber(groupDTO.getStNumber())
                .days(groupDTO.getDays())
                .startTime(groupDTO.getStartTime())
                .endTime(groupDTO.getEndTime())
                .startDate(groupDTO.getStartDate())
                .endDate(groupDTO.getEndDate())
                .status(groupDTO.getStatus() != null ? groupDTO.getStatus() : Status.ACTIVE)
                .build();

        Group savedGroup = groupRepository.save(group);
        return convertToDTO(savedGroup);
    }

    @Transactional
    public GroupDTO updateGroup(Long id, GroupDto groupDTO) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> RestException.notFound("Group not found", id));
        if (group.isDeleted()) {
            throw RestException.notFound("Group not found", id);
        }

        group.setGroupName(groupDTO.getName());
        if (groupDTO.getCourseId() != null) {
            Course course = courseRepository.findById(groupDTO.getCourseId())
                    .orElseThrow(() -> RestException.notFound("Course not found", groupDTO.getCourseId()));
            group.setCourse(course);
        }
        if (groupDTO.getRoomId() != null) {
            Room room = roomRepository.findById(groupDTO.getRoomId())
                    .orElseThrow(() -> RestException.notFound("Room not found", groupDTO.getRoomId()));
            group.setRoom(room);
        }
        group.setStNumber(groupDTO.getStNumber());
        group.setDays(groupDTO.getDays());
        group.setStartTime(group.getStartTime());
        group.setEndTime(group.getEndTime());
        group.setStartDate(group.getStartDate());
        group.setEndDate(group.getEndDate());
        if (groupDTO.getStatus() != null) {
            group.setStatus(groupDTO.getStatus());
        }

        Group updatedGroup = groupRepository.save(group);
        return convertToDTO(updatedGroup);
    }

    // Delete: Soft delete a group
    @Transactional
    public GroupDTO deleteGroup(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> RestException.notFound("Group not found", id));
        if (group.isDeleted()) {
            throw RestException.error("Group already deleted");
        }
        GroupDTO dto = convertToDTO(group);
        groupRepository.delete(group); // Soft delete via @SQLDelete
        return dto;
    }

    private GroupDTO convertToDTO(Group group) {
        return GroupDTO.builder()
                .id(group.getId())
                .name(group.getGroupName())
                .courseId(group.getCourse() != null ? group.getCourse().getId() : null)
                .roomId(group.getRoom() != null ? group.getRoom().getId() : null)
                .stNumber(group.getStNumber())
                .days(group.getDays())
                .startTime(group.getStartTime())
                .endTime(group.getEndTime())
                .startDate(group.getStartDate())
                .endDate(group.getEndDate())
                .status(group.getStatus())
                .build();
    }
}