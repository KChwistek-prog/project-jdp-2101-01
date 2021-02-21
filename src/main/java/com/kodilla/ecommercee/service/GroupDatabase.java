package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GroupDatabase {
    @Autowired
    private final GroupRepository groupRepository;

    public List<Group> getAllGroups(){
        return groupRepository.findAll();
    }
    public Optional<Group> getGroupById(final Long Id){
        return groupRepository.findById(Id);
    }
    public Group saveGroup(final Group group){
        return groupRepository.save(group);
    }
}
