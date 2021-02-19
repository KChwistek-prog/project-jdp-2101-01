package com.kodilla.ecommercee.domain;

import lombok.*;

@Getter
@AllArgsConstructor
public class OrderDto {
    private Long orderId;

    private CartDto cartDto;

    private String orderStatus;

    private String paymentMethod;

    private Double totalValue;

    private java.sql.Timestamp paymentDate;

    private java.sql.Timestamp realisationDate;

    private boolean isPaid;
}
