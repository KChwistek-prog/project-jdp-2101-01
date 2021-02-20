package com.kodilla.ecommercee.ProductTestSuite;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.Assert.*;


@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTestSuite {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testProductCreate() {

        //Given
        List<Product> listOfProduct = new LinkedList<>();
        Group group = new Group("Name of group","desc", listOfProduct);
        Set<Cart> setOfCart = new HashSet<>();
        Product product = new Product("Name of product", "desc", 12.34, "szt.", group, setOfCart);

        //When
        productRepository.save(product);
        long id = product.getProductId();

        //Then
        assertTrue(productRepository.findById(id).isPresent());

        //CleanUp
        productRepository.deleteById(id);

    }

    @Test
    public void testProductRead() {

        //Given
        List<Product> listOfProduct = new LinkedList<>();
        Group group = new Group("Name of group","desc", listOfProduct);
        Set<Cart> setOfCart = new HashSet<>();
        Product product = new Product("Name of product", "desc", 12.34, "szt.", group, setOfCart);

        //When
        productRepository.save(product);
        long id = product.getProductId();
        Product productName = productRepository.findById(id).get();

        //Then
        assertEquals("Name of product", productName.getName());

        //CleanUp
        productRepository.deleteById(id);

    }

    @Test
    public void testProductUpdate() {

        //Given
        List<Product> listOfProduct = new LinkedList<>();
        Group group = new Group("Name of group","desc", listOfProduct);
        Set<Cart> setOfCart = new HashSet<>();
        Product product = new Product("Name of product", "desc", 12.34, "szt.", group, setOfCart);

        //When
        productRepository.save(product);
        long id = product.getProductId();
        productRepository.findById(id).get().setDescription("Changed name of product");
        Product productName = productRepository.findById(id).get();

        //Then
        assertEquals("Changed name of product", productName.getDescription());

        //CleanUp
        productRepository.deleteById(id);

    }

    @Test
    public void testProductDelete() {

        //Given
        Set<Cart> setOfCarts = new HashSet<>();
        List<Product> listOfProducts = new LinkedList<Product>();
        Group group = new Group("Name of group","desc", listOfProducts);
        Product firstProduct = new Product("Name of first product", "Desc of first product", 12.34, "szt.", group, setOfCarts);
        Product secondProduct = new Product("Name of second product", "Desc of second product", 12.34, "szt.", group, setOfCarts);
        listOfProducts.add(firstProduct);
        listOfProducts.add(secondProduct);

        //When
        productRepository.save(firstProduct);
        productRepository.save(secondProduct);

        long idFirstProduct = firstProduct.getProductId();
        long idSecondProduct = secondProduct.getProductId();

        //Then
        assertTrue(productRepository.findById(idFirstProduct).isPresent());
        productRepository.deleteById(idFirstProduct);
        assertFalse(productRepository.findById(idFirstProduct).isPresent());
        assertTrue(productRepository.findById(idSecondProduct).isPresent());

        //CleanUp
        productRepository.deleteById(idSecondProduct);

    }


}