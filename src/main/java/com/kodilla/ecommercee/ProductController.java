package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/Product")
@CrossOrigin("*")
public class ProductController {

    @GetMapping("getAllProducts")
    public List<ProductDto> getAllProducts() {
        return new ArrayList<>();
    }

    @GetMapping("getProduct/{productId}")
    public ProductDto getProduct(@PathVariable Long productId) {
        return new ProductDto(1L, "Name of product", "Description of product", 200, "szt.", new Group("Name of group", "Desc of group", new ArrayList<Product>()));
    }

    @PostMapping("addProduct")
    public void addProduct(@RequestBody ProductDto productDto) {
        System.out.println("Adding new product.");
    }

    @PutMapping("updateProduct/{productId}")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return new ProductDto(1L, "New name of product", "New description of product", 678.9, "szt.", new Group("Name of group", "Desc of group", new ArrayList<Product>()));
    }

    @DeleteMapping("deleteProduct/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        System.out.println("Deleting product.");
    }
}