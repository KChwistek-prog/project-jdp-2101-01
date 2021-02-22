package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;

import static org.junit.Assert.*;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTestSuite {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CartRepository cartRepository;

    @Test
    public void saveOrderTest(){
        //given
        Order orderSave = new Order("testStatus", "testMethod", 250.0, new Timestamp(12345678900L), new Timestamp(12345678900L), true);

        //when
        orderRepository.save(orderSave);

        //then
        assertTrue(orderRepository.findById(orderSave.getOrderId()).isPresent());

        //cleanUp
        orderRepository.deleteById(orderSave.getOrderId());
    }

    @Test
    public void readOrderTest(){
        //given
        Order orderRead = new Order("testStatus", "testMethod", 250.0, new Timestamp(12345678900L), new Timestamp(12345678900L), true);

        orderRepository.save(orderRead);

        //when
        String orderStatus = orderRepository.findById(orderRead.getOrderId()).get().getOrderStatus();

        //then
        assertEquals("testStatus", orderStatus);

        //cleanUp
        orderRepository.deleteById(orderRead.getOrderId());

    }

    @Test
    public void updateOrderTest(){
        //given
        Order orderUpdate = new Order("testStatus", "testMethod", 250.0, new Timestamp(12345678900L), new Timestamp(12345678900L), true);
        orderRepository.save(orderUpdate);

        //when
        orderRepository.save(orderUpdate).setOrderStatus("updatedStatus");
        String status = orderRepository.findById(orderUpdate.getOrderId()).get().getOrderStatus();

        //then
        assertEquals("updatedStatus", status);

        //cleanUp
        orderRepository.deleteById(orderUpdate.getOrderId());

    }

    @Test
    public void deleteOrderTest(){
        //given
        Order orderDelete = new Order("testStatus", "testMethod", 250.0, new Timestamp(12345678900L), new Timestamp(12345678900L), true);
        Cart cart = new Cart(orderDelete, new User(), new Date(12345678900L), new Date(12345900000L), false, new HashSet<>());
        orderRepository.save(orderDelete);
        cartRepository.save(cart);
        orderRepository.save(orderDelete).setCart(cart);

        //when
        orderRepository.deleteById(orderDelete.getOrderId());

        //then
        assertFalse(orderRepository.findById(orderDelete.getOrderId()).isPresent());
        assertFalse(cartRepository.findById(cart.getCartId()).isPresent());
    }

}

