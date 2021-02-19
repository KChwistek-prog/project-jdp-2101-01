package com.kodilla.ecommercee.user.dao;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTestSuite {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void UserDaoCreate() {
        //Given
        User user = new User("TestUserName", "TestPassword","Test@email.com");

        //When
        userRepository.save(user);

        Long id = user.getUserId();
        //Then
        assertTrue(userRepository.findById(id).isPresent());

        //CleanUp
        userRepository.deleteById(id);
    }

    @Test
    public void UserDaoRead() {
        //Given
        User user = new User("TestUserName", "TestPassword","Test@email.com");

        //When
        userRepository.save(user);
        Long id = user.getUserId();
        userRepository.findById(id);
        String userName = user.getUserName();

        User searchUser = userRepository.findByUserName(userName);
        //Then
        assertEquals("TestUserName", searchUser.getUserName());

        //CleanUp
        userRepository.deleteById(id);
    }

    @Test
    public void UserDaoUpdate() {
        //Given
        User user = new User("TestUserName", "TestPassword","Test@email.com");
        //user.setEmailAddress("my@new.address");

        //When
        userRepository.save(user);
        Long id = user.getUserId();
        userRepository.findByUserName("TestUserName").setEmailAddress("my@new.address");

        User updateUserEmail = userRepository.findByUserName("TestUserName");
        //Then
        assertEquals("my@new.address", updateUserEmail.getEmailAddress());

        //CleanUp
        userRepository.deleteById(id);
    }

    @Test
    public void UserDaoDelete() {
        //Given
        User userOne = new User("TestUserName", "TestPassword","Test@email.com");
        User userTwo = new User("UserToDelete", "PasswordToDelete", "delete@me.com");

        //When
        userRepository.save(userOne);
        Long userOneId = userOne.getUserId();
        userRepository.save(userTwo);
        Long userTwoId = userTwo.getUserId();

        //Then
        assertTrue(userRepository.findById(userTwoId).isPresent());
        userRepository.deleteById(userTwoId);
        assertFalse(userRepository.findById(userTwoId).isPresent());

        //CleanUp
        userRepository.deleteById(userOneId);
    }
}
