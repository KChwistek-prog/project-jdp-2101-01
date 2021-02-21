package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartDatabase {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private final ProductDbService productDatabase;

    @Autowired
    private final CartMapper cartMapper;


    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart getCart(Long cartId) {
        return cartRepository.findById(cartId).orElse(new Cart());
    }

    public CartDto addProduct(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId).orElse(new Cart());
        cart.getListOfProducts().add(productDatabase.showProduct(productId).get());
        return cartMapper.mapToCartDto(cartRepository.save(cart));
    }

    public void deleteProduct(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new IllegalArgumentException("Cart not found"));
        Product productDelete = productDatabase.showProduct(productId).get();
        cart.getListOfProducts().remove(productDelete);
        cartMapper.mapToCartDto(cartRepository.save(cart));
    }
}
