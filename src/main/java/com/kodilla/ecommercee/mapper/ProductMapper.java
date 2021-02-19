package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductMapper {

    public Product mapToProduct(final ProductDto productDto){
        return new Product(
                productDto.getProductId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                productDto.getUnit(),
                productDto.getGroupId(),
                null
        );
    }

    public ProductDto mapToProductDto(final Product product){
        return new ProductDto(
                product.getProductId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getUnit(),
                product.getGroups(),
                null
        );
    }

    public List<ProductDto> mapToProductDtoList(final List<Product> productList){
        return productList.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }
}






