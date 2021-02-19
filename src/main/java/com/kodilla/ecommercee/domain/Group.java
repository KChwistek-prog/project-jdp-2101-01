package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "GRUPY")
public class Group {

    public Group(String name, String description, List<Product> products) {

        this.name = name;
        this.description = description;
        this.products = products;

    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "groups",
            fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList();

}