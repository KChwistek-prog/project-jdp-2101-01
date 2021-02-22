package com.kodilla.ecommercee.user.dao;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTestSuite {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Test
    public void UserDaoCreate() {
        //Given
        User user = new User(
                "TestUserName",
                "Jan",
                "Kowalski",
                "TestPassword",
                "Test@email.com",
                "TestAddress",
                "123456789");

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
        User user = new User(
                "TestUserName",
                "Jan",
                "Kowalski",
                "TestPassword",
                "Test@email.com",
                "TestAddress",
                "123456789");

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
        User user = new User(
                "TestUserName",
                "Jan",
                "Kowalski",
                "TestPassword",
                "Test@email.com",
                "TestAddress",
                "123456789");

        //When
        userRepository.save(user);
        Long id = user.getUserId();
        userRepository.findByUserId(id).setEmailAddress("my@new.address");

        User updateUserEmail = userRepository.findByUserName("TestUserName");
        //Then
        assertEquals("my@new.address", updateUserEmail.getEmailAddress());

        //CleanUp
        userRepository.deleteById(id);
    }

    @Test
    public void UserDaoDelete() {
        //Given
        Set<Cart> setCart = new HashSet<>();
        Order order = new Order();
        Date dateOfReservation = new Date(12345678900L);
        Date endOfReservation = new Date(12345900000L);
        Set<Product> listOfProducts = new HashSet<>();

        //When
        User user = new User("TestUserName","Jan","Kowalski","TestPassword","Test@email.com", "TestAddress","123456789");
        userRepository.save(user);
        Long userId = user.getUserId();

        Cart cart = new Cart(order, user, dateOfReservation, endOfReservation,false, listOfProducts);
        cartRepository.save(cart);
        Long cartId = cart.getCartId();
        setCart.add(cart);
        user.setCarts(setCart);

        //Then
        assertTrue(userRepository.findById(userId).isPresent());
        userRepository.deleteById(userId);
        assertFalse(userRepository.findById(userId).isPresent());
        assertFalse(cartRepository.findById(cartId).isPresent());
    }
}
