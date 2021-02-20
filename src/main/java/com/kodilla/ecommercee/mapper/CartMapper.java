package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartMapper {
    OrderMapper orderMapper;
    UserMapper userMapper;
    public Cart mapToCart(final CartDto cartDto) {
        return new Cart(
                cartDto.getCartId(),
                orderMapper.mapToOrder(cartDto.getOrderDto()),
                userMapper.mapToUser(cartDto.getUserDto()),
                cartDto.getDateOfReservation(),
                cartDto.getTermOfEndReservation(),
                cartDto.getIsOrdered(),
                cartDto.getUserDto().getCart().getListOfProducts()
        );
    }

    public CartDto mapToCartDto(final Cart cart){
        return new CartDto(
                cart.getCartId(),
                orderMapper.mapToOrderDto(cart.getOrder()),
                userMapper.mapToUserDto(cart.getUser()),
                cart.getDateOfReservation(),
                cart.getTermOfEndReservation(),
                cart.getIsOrdered(),
                cart.getListOfProducts()
        );
    }

    public List<CartDto> mapToCartDtoList(final List<Cart> cartList){
        return cartList.stream()
                .map(this::mapToCartDto)
                .collect(Collectors.toList());
    }
}
