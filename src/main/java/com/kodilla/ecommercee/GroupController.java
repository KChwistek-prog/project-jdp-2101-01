package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.GroupDto;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/Group")
@CrossOrigin("*")
public class GroupController {

    @RequestMapping(method = RequestMethod.GET, value = "getAllGroups")
    public List<GroupDto> getAllGroups() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.POST, value = "addGroup")
    public GroupDto addGroup(@RequestBody GroupDto groupDto) {
        return new GroupDto(2L, "Test group", "Description of added test group");
    }

    @RequestMapping(method = RequestMethod.GET, value = "getGroupWithId")
    public GroupDto getGroupWithId(@RequestParam Long groupId) {
        return new GroupDto(1L, "Test name", "test_description");
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateGroup")
    public GroupDto updateGroup(@RequestBody GroupDto groupDto) {
        return new GroupDto(1L, "Edited test name", "edited test description");
    }
}