package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    public Product(String name, String description, Double price, String unit, Group groups, Set<Cart> carts) {
        this.name=name;
        this.description=description;
        this.price=price;
        this.unit=unit;
        this.groups=groups;
        this.carts=carts;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    @Column(name = "PRODUCT_ID", unique = true)
    private Long productId;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private double price;

    @Column
    private String unit;

    @ManyToOne
    @JoinColumn(name = "GRUPY_ID")
    private Group groups;

    @ManyToMany
    private Set<Cart> carts;
}