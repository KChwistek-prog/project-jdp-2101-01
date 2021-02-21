package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carts")
public class Cart {

    public Cart(Order order, User user, Date dateOfReservation, Date termOfEndReservation, Boolean isOrdered, Set<Product> listOfProducts ){
        this.order = order;
        this.user = user;
        this.dateOfReservation = dateOfReservation;
        this.termOfEndReservation = termOfEndReservation;
        this.isOrdered = isOrdered;
        this.listOfProducts = listOfProducts;
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column
    private Long cartId;

    @OneToOne(cascade = CascadeType.REMOVE)  //JDP210101-19
    @MapsId
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(cascade = CascadeType.PERSIST)  //JDP210101-19
    @JoinColumn(name = "USER")
    private User user;

    @Column(name = "DATE_OF_RESERVATION")
    private Date dateOfReservation;

    @Column(name = "TERM_OF_END_RESERVATION")
    private Date termOfEndReservation;

    @Column(name = "IS_ORDERED")
    private Boolean isOrdered;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Product> listOfProducts;

    public void addProduct(Product product) {
        listOfProducts.add(product);
        product.getCarts().add(this);
    }
}
