package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.util.Date;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CartDto {
    private Long cartId;
    private OrderDto orderDto;
    private UserDto userDto;
    private Date dateOfReservation;
    private Date termOfEndReservation;
    private Boolean isOrdered;
    private Set<ProductDto> listOfProducts;
}
