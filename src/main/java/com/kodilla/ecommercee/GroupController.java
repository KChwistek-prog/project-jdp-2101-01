package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.GroupDto;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.GroupDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/Group")
@CrossOrigin("*")
public class GroupController {
    private final GroupDatabase groupDatabase;
    private final GroupMapper groupMapper;

    @Autowired
    public GroupController(GroupDatabase groupDatabase , GroupMapper groupMapper){
        this.groupDatabase = groupDatabase;
        this.groupMapper = groupMapper;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAllGroups")
    public List<GroupDto> getAllGroups() {
        List<Group> groups = groupDatabase.getAllGroups();
        return groupMapper.mapToGroupDtoList(groups);
    }

    @RequestMapping(method = RequestMethod.POST, value = "addGroup")
    public GroupDto addGroup(@RequestBody GroupDto groupDto) {
        Group savingGroup =groupDatabase.saveGroup(groupMapper.mapToGroup(groupDto));
        return groupMapper.mapToGroupDto(savingGroup);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getGroupWithId")
    public GroupDto getGroupWithId(@RequestParam Long groupId) {
        Optional<Group> searchedGroup = groupDatabase.getGroupById(groupId);
        return groupMapper.mapToGroupDto(searchedGroup.orElseThrow(RuntimeException::new));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateGroup")
    public GroupDto updateGroup(@RequestBody GroupDto groupDto) {
        Group updatingGroup =groupDatabase.saveGroup(groupMapper.mapToGroup(groupDto));
        return groupMapper.mapToGroupDto(updatingGroup);
    }
}