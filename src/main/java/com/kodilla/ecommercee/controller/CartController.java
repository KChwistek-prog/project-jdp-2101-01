package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.CartDatabase;
import com.kodilla.ecommercee.service.OrderDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/Cart")
@CrossOrigin("*")
public class CartController {

    private final CartDatabase cartDatabase;
    private final CartMapper cartMapper;
    private final OrderDatabase orderDatabase;
    private final OrderMapper orderMapper;

    @Autowired
    public CartController(CartDatabase cartDatabase, CartMapper cartMapper, OrderDatabase orderDatabase, OrderMapper orderMapper) {
        this.cartDatabase = cartDatabase;
        this.cartMapper = cartMapper;
        this.orderDatabase = orderDatabase;
        this.orderMapper = orderMapper;
    }

    @RequestMapping(method = RequestMethod.POST, value = "getNewCart")
    public void saveCart(@RequestBody CartDto cartDto) {
        cartDatabase.saveCart(cartMapper.mapToCart(cartDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getProducts")
    public CartDto getProductsFromCart(@RequestParam Long cartId) {
        return cartMapper.mapToCartDto(cartDatabase.getCart(cartId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "addProducts")
    public CartDto addProduct(@RequestParam Long cartId, @RequestParam Long productId) {
        return cartDatabase.addProduct(cartId, productId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProduct")
    public void deleteProductFromCart(@RequestParam Long cartId, @RequestParam Long productId) {
        cartDatabase.deleteProduct(cartId, productId);
    }

    @PostMapping(value = "createOrder", consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        return orderMapper.mapToOrderDto(orderDatabase.saveOrder(orderMapper.mapToOrder(orderDto)));
    }
}
