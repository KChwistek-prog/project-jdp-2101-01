package com.kodilla.ecommercee.group.dao;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

@EnableTransactionManagement
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class CartTestSuite {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void TestCartDaoCreate() {
        //Give
        Set<Product> setProduct = new HashSet<>();
        Set<Cart> setCart = new HashSet<>();
        Product product = new Product("klucz", "klucz francuski", 500.0, "szt.",new Group() , setCart);
        setProduct.add(product);
        Order order = new Order("status testowy", "payment testowy", 500.0,
                new Timestamp(12345678900L), new Timestamp(12345900000L), false);
        User user = new User("SuperUser","Marcin", "Kowalski", "Alamakota",
                "Marcin11@gmail.com", "ul. Podleśna 12 / 17", "669-257-812");
        Cart cart = new Cart(order, user, new Date(12345678900L), new Date(12345900000L), false, setProduct);

        //When
        cartRepository.save(cart);
        Long id = cart.getCartId();

        //Then
        assertTrue(cartRepository.findById(id).isPresent());

        //CleanUp
        cartRepository.deleteById(id);
    }
    @Test
    public void TestCartDaoRead() {
        //Give
        Set<Product> setProduct = new HashSet<>();
        Set<Cart> setCart = new HashSet<>();
        Product product = new Product("klucz", "klucz francuski", 500.0, "szt.", new Group(), setCart);
        setProduct.add(product);
        Order order = new Order("status testowy", "payment testowy", 500.0,
                new Timestamp(12345678900L), new Timestamp(12345900000L), false);
        User user = new User("SuperUser", "Marcin", "Kowalski", "Alamakota",
                "Marcin11@gmail.com", "ul. Podleśna 12 / 17", "669-257-812");
        Cart cart = new Cart(order, user, new Date(12345678900L), new Date(12345900000L), true, setProduct);

        //When
        cartRepository.save(cart);
        Long id = cart.getCartId();

        //Then
        Optional<Cart> readCart = cartRepository.findById(id);
        assertEquals( new Date(12345678900L), readCart.map(Cart::getDateOfReservation).orElse(new Date(1L)));
        assertEquals( new Date(12345900000L), readCart.map(Cart::getTermOfEndReservation).orElse(new Date(1L)));
        assertEquals(true , readCart.map(Cart::getIsOrdered).orElse(false));

        //CleanUp
        cartRepository.deleteById(id);
    }
    @Test
    public void TestCartDaoModification() {
        //Give
        Set<Product> setProduct = new HashSet<>();
        Set<Cart> setCart = new HashSet<>();
        Product product = new Product("klucz", "klucz francuski", 500.0, "szt.", new Group(), setCart);
        setProduct.add(product);
        Order order = new Order("status testowy", "payment testowy", 500.0,
                new Timestamp(12345678900L), new Timestamp(12345900000L), false);
        User user = new User("SuperUser", "Marcin", "Kowalski", "Alamakota",
                "Marcin11@gmail.com", "ul. Podleśna 12 / 17", "669-257-812");
        Cart cart = new Cart(order, user, new Date(12345678900L), new Date(12345900000L), false, setProduct);

        //When
        cartRepository.save(cart);
        Long id = cart.getCartId();

        //Then
        assertTrue(cartRepository.findById(id).isPresent());

        cartRepository.findById(id).get().setDateOfReservation(new Date(15345678900L));
        cartRepository.findById(id).get().setTermOfEndReservation(new Date(15345900000L));
        cartRepository.findById(id).get().setIsOrdered(true);

        assertEquals( new Date(15345678900L), cartRepository.findById(id).get().getDateOfReservation());
        assertEquals( new Date(15345900000L), cartRepository.findById(id).get().getTermOfEndReservation());
        assertEquals(true , cartRepository.findById(id).get().getIsOrdered());

        //CleanUp
        cartRepository.deleteById(id);
    }
    @Test
    public void TestCartDaoDelete() {
        //Give
        Set<Product> setProduct = new HashSet<>();
        Set<Cart> setCart = new HashSet<>();
        Product product = new Product("klucz", "klucz francuski", 500.0, "szt.", new Group(), setCart);
        setProduct.add(product);
        Order order = new Order("status testowy", "payment testowy", 500.0,
                new Timestamp(12345678900L), new Timestamp(12345900000L), false);
        User user = new User("SuperUser", "Marcin", "Kowalski", "Alamakota",
                "Marcin11@gmail.com", "ul. Podleśna 12 / 17", "669-257-812");
        Cart cart = new Cart(order, user, new Date(12345678900L), new Date(12345900000L), false, setProduct);

        //When
        Cart cartS = cartRepository.save(cart);
        Long orderID = cartS.getOrder().getOrderId();
        Long userID = cartS.getUser().getUserId();
        System.out.println("UserID = "+cartS.getUser().getUserId()+"User name = "+cartS.getUser().getUserName());
        Long productID = cartS.getListOfProducts().stream().iterator().next().getProductId();

        //Then
        assertTrue(cartRepository.findById(cartS.getCartId()).isPresent());
        cartRepository.delete(cartRepository.findById(cartS.getCartId()).get());
        assertFalse(cartRepository.findById(cartS.getCartId()).isPresent());
        assertFalse(orderRepository.findById(orderID).isPresent());
        assertTrue(userRepository.findById(userID).isPresent());
        assertTrue(productRepository.findById(productID).isPresent());
    }
}
