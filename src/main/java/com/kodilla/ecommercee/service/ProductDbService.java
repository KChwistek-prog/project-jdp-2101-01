package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductDbService {

    @Autowired
    private final ProductRepository repository;

    public List<Product> showAllProducts() {
        return repository.findAll();
    }

    public Optional<Product> showProduct(final Long productId){
        return repository.findById(productId);
    }

    public void addProduct(final Product product){

    }

    public Product modifyProduct(final Product product){
        return repository.save(product);
    }

    public void removeProduct(final Long productId){
        repository.deleteById(productId);
    }
}
