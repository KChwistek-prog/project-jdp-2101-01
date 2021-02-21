package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class ProductDto {
    private Long productId;
    private String name;
    private String description;
    private double price;
    private String unit;
    private GroupDto groupId;
}






