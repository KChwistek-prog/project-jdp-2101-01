package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserDatabase {

    @Autowired
    private final UserRepository userRepository;

    public User saveUser(final User user){
        return userRepository.save(user);
    }

    public void deleteUser(final Long userId){
        userRepository.deleteById(userId);
    }

    public List<User> showAllUsers(){
        return userRepository.findAll();
    }
}
