package com.kodilla.ecommercee.mapper;


import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {
    CartMapper cartMapper;
    public Order mapToOrder(final OrderDto orderDto){
        return new Order(
                orderDto.getOrderId(),
                cartMapper.mapToCart(orderDto.getCartDto()),
                orderDto.getOrderStatus(),
                orderDto.getPaymentMethod(),
                orderDto.getTotalValue(),
                orderDto.getPaymentDate(),
                orderDto.getRealisationDate(),
                orderDto.isPaid()
        );
    }

    public OrderDto mapToOrderDto(final Order order){
        return new OrderDto(
                order.getOrderId(),
                cartMapper.mapToCartDto(order.getCart()),
                order.getOrderStatus(),
                order.getPaymentMethod(),
                order.getTotalValue(),
                order.getPaymentDate(),
                order.getRealisationDate(),
                order.isPaid()
        );
    }

    public List<OrderDto> mapToOrderDtoList(final List<Order> orderList){
        return orderList.stream()
                .map(this::mapToOrderDto)
                .collect(Collectors.toList());
    }
}

