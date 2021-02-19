package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserDatabase;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/user")
@CrossOrigin("*")
public class UserController {
    private final UserDatabase userDatabase;
    private final UserMapper userMapper;

    public UserController(UserDatabase userDatabase, UserMapper userMapper) {
        this.userDatabase = userDatabase;
        this.userMapper = userMapper;
    }

    @PostMapping(value = "createNewUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
            userDatabase.saveUser(userMapper.mapToUser(userDto));
    }

    @DeleteMapping(value = "deleteUser")
    public void banUser(@RequestParam Long userId) {
      userDatabase.deleteUser(userId);
    }

    @PutMapping(value = "editUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User editUser(@RequestBody UserDto userDto){
        return userDatabase.saveUser(userMapper.mapToUser(userDto));
    }

    @GetMapping(value = "getUsers")
    public List<UserDto> getUsers(){
       List<User> users = userDatabase.showAllUsers();
       return userMapper.mapToUserDtoList(users);
    }
}
