package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

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

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Cart> carts = new ArrayList<>();

}