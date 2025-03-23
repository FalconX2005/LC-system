//package uz.pdp.lcsystem.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//import uz.pdp.lcsystem.payload.ApiResult;
//import uz.pdp.lcsystem.payload.GroupDTO;
//import uz.pdp.lcsystem.service.GroupService;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/group")
//@RequiredArgsConstructor
//public class GroupController {
//
//    private final GroupService groupService;
//
//    @GetMapping
//    public ApiResult<List<GroupDTO>> getAllGroups() {
//        List<GroupDTO> groups = groupService.getAllGroups();
//        return ApiResult.success(groups);
//    }
//
//    @GetMapping("/{id}")
//    public ApiResult<GroupDTO> getGroupById(@PathVariable Long id) {
//        GroupDTO group = groupService.getGroupById(id);
//        return ApiResult.success(group);
//    }
//
//    @PostMapping
//    public ApiResult<GroupDTO> createGroup(@RequestBody GroupDTO groupDTO) {
//        GroupDTO createdGroup = groupService.createGroup(groupDTO);
//        return ApiResult.success(createdGroup);
//    }
//
//    @PutMapping("/{id}")
//    public ApiResult<GroupDTO> updateGroup(@PathVariable Long id, @RequestBody GroupDTO groupDTO) {
//        GroupDTO updatedGroup = groupService.updateGroup(id, groupDTO);
//        return ApiResult.success(updatedGroup);
//    }
//
//    @DeleteMapping("/{id}")
//    public ApiResult<GroupDTO> deleteGroup(@PathVariable Long id) {
//        GroupDTO deletedGroup = groupService.deleteGroup(id);
//        return ApiResult.success(deletedGroup);
//    }
//}

package uz.pdp.lcsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lcsystem.payload.ApiResult;
import uz.pdp.lcsystem.payload.GroupDTO;
import uz.pdp.lcsystem.service.GroupService;

import java.util.List;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @Operation(summary = "Get all groups", description = "Retrieve a list of all groups")
    @GetMapping
    public ApiResult<List<GroupDTO>> getAllGroups() {
        List<GroupDTO> groups = groupService.getAllGroups();
        return ApiResult.success(groups);
    }

    @Operation(summary = "Get group by ID", description = "Retrieve a group using its unique ID")
    @GetMapping("/{id}")
    public ApiResult<GroupDTO> getGroupById(@PathVariable Long id) {
        GroupDTO group = groupService.getGroupById(id);
        return ApiResult.success(group);
    }

    @Operation(summary = "Create a new group", description = "Create a new group with course, teacher, students, and room")
    @PostMapping
    public ApiResult<GroupDTO> createGroup(@RequestBody GroupDTO groupDTO) {
        GroupDTO createdGroup = groupService.createGroup(groupDTO);
        return ApiResult.success(createdGroup);
    }

    @Operation(summary = "Update a group", description = "Update an existing group by ID")
    @PutMapping("/{id}")
    public ApiResult<GroupDTO> updateGroup(@PathVariable Long id, @RequestBody GroupDTO groupDTO) {
        GroupDTO updatedGroup = groupService.updateGroup(id, groupDTO);
        return ApiResult.success(updatedGroup);
    }

    @Operation(summary = "Delete a group", description = "Delete a group by ID")
    @DeleteMapping("/{id}")
    public ApiResult<GroupDTO> deleteGroup(@PathVariable Long id) {
        GroupDTO deletedGroup = groupService.deleteGroup(id);
        return ApiResult.success(deletedGroup);
    }
}
