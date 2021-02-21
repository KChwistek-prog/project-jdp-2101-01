package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.ProductDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/product")
public class ProductController {
    private final ProductDbService dbService;
    private final ProductMapper mapper;

    @Autowired
    public ProductController(ProductDbService dbService, ProductMapper mapper) {
        this.dbService = dbService;
        this.mapper = mapper;
    }

    @GetMapping("getAllProducts")
    public List<ProductDto> getAllProducts() {
        List<Product> products = dbService.showAllProducts();
        return mapper.mapToProductDtoList(products);
    }

    @GetMapping("getProduct/{productId}")
    public ProductDto showProduct(@PathVariable Long productId) throws ProductNotFoundException {
        Optional<Product> product = dbService.showProduct(productId);
        return mapper.mapToProductDto(product.get());
    }

    @PostMapping("addProduct")
    public void addProduct(@RequestBody ProductDto productDto) {
        dbService.addProduct();
    }

    @PutMapping("updateProduct/{productId}")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        Product product = dbService.modifyProduct(mapper.mapToProduct(productDto));
        return mapper.mapToProductDto(product);
    }

    @DeleteMapping("deleteProduct/{productId}")
    public void removeProduct(@PathVariable Long productId) {
        dbService.removeProduct(productId);
    }
}