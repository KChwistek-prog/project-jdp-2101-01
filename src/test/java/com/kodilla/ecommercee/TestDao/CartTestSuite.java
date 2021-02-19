package com.kodilla.ecommercee.TestDao;
import com.kodilla.ecommercee.repository.OrderRepository;
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
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;
import java.util.*;
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

    @Test
    public void TestCartDaoCreate(){
        //Give
        Set<Cart> set = new HashSet<>();
        Order order = new Order("status testowy", "payment testowy", 500.0,
                new Timestamp(12345678900L),new Timestamp(12345900000L),false);
        User user1 = new User("Marcin","Alamakota","Marcin11@gmail.com");
        Cart cart= new Cart(order , user1, new Date(12345678900L),new Date(12345900000L),false, set);

        //When
        cartRepository.save(cart);
        Long id = cart.getCartId();
        //Then

        assertTrue(cartRepository.findById(id).isPresent());
        //CleanUp

        cartRepository.deleteById(id);

    }
    @Test
    public void TestCartDaoRead(){
        //Give

        Set<Cart> set = new HashSet<>();
        Order order = new Order("status testowy", "payment testowy", 500.0,
                new Timestamp(12345678900L),new Timestamp(12345900000L),false);
        User user = new User("Marcin","Alamakota","Marcin11@gmail.com");
        Cart cart = new Cart(order, user, new Date(12345678900L), new Date(12345900000L) ,true, set );

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
    public void TestCartDaoModification(){
        //Give

        Set<Cart> set = new HashSet<>();
        Order order = new Order("status testowy", "payment testowy", 500.0,
                new Timestamp(12345678900L),new Timestamp(12345900000L),false);;
        User user = new User("Marcin","Alamakota","Marcin11@gmail.com");
        Cart cart1 = new Cart(order, user, new Date(12345678900L), new Date(12345900000L) ,false, set );

        //When

        cartRepository.save(cart1);
        Long id = cart1.getCartId();

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
        Set<Cart> set = new HashSet<>();
        Order order = new Order("status testowy", "payment testowy", 500.0,
                new Timestamp(12345678900L),new Timestamp(12345900000L),false);
        User user1 = new User("Marcin","Alamakota","Marcin11@gmail.com");
        Cart cart1 = new Cart(order,  user1, new Date(12345678900L), new Date(12345900000L) ,false, set );
        //When
        Cart cartS = cartRepository.save(cart1);
        Long orderID = cartS.getOrder().getOrderId();
        Long userID = cartS.getUser().getUserId();
        //Then
        assertTrue(cartRepository.findById(cartS.getCartId()).isPresent());
        cartRepository.delete(cartRepository.findById(cartS.getCartId()).get());
        assertFalse(cartRepository.findById(cartS.getCartId()).isPresent());
        assertFalse(orderRepository.findById(orderID).isPresent());
        assertTrue(userRepository.findById(userID).isPresent());
    }
}

