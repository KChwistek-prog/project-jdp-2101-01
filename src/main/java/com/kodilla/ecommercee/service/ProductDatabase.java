package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductDatabase {

    @Autowired
    private ProductRepository productRepository;

    public Product getProduct(Long productId) {
         return productRepository.findById(productId).orElse(new Product());
    }

    public Product deleteProduct(Long productId) {
            return productRepository.findById(productId).get();

    }
}
