package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;

@RestController
@RequestMapping("/v1/Cart")
@CrossOrigin("*")
public class CartController {
    private CartDto cartDto;
    private OrderDto orderDto;
    private UserDto user;

    @RequestMapping(method = RequestMethod.GET, value = "getNewCart")
    public CartDto createNewCart(@RequestParam Long userId) {
        return new CartDto(1L,orderDto, user, new Timestamp(20200525123420L), new Timestamp(20200525123420L)  ,true, Collections.emptySet());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getProducts")
    public ProductDto getProducts(@RequestParam Long cartId) {
        return new ProductDto(1L,"New Product","New description",20.20, "szt.", new Group("Name of group", "Desc of group", new ArrayList<Product>()));
    }

    @RequestMapping(method = RequestMethod.POST, value = "addProducts")
    public ProductDto addProduct(@RequestParam Long cartId) {
        return new ProductDto(2L,"Added Product","Added description",60.60, "szt.", new Group("Name of group", "Desc of group", new ArrayList<Product>()));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProduct")
    public ProductDto deleteProduct(@RequestParam Long cartId) {
        return new ProductDto(3L,"Deleted product","Deleted description",100.100, "szt.", new Group("Name of group", "Desc of group", new ArrayList<Product>()));
    }

    @PostMapping(value = "createOrder", consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {

        return new OrderDto(1L, cartDto, "done","card",2.0, new Timestamp(20200525123420L),new Timestamp(20200525123420L), true);
    }
}
