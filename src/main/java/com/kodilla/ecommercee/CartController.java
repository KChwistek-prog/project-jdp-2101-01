package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.domain.ProductDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/Cart")
@CrossOrigin("*")
public class CartController {

    @RequestMapping(method = RequestMethod.GET, value = "getNewCart")
    public CartDto createNewCart(@RequestParam Long userId) {
        return new CartDto(1L,"first cart");
    }

    @RequestMapping(method = RequestMethod.GET, value = "getProducts")
    public ProductDto getProducts(@RequestParam Long cartId) {
        return new ProductDto(1L,"New Product","New description",20.20,5L);
    }

    @RequestMapping(method = RequestMethod.POST, value = "addProducts")
    public ProductDto addProduct(@RequestParam Long cartId) {
        return new ProductDto(2L,"Added Product","Added description",60.60,6L);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProduct")
    public ProductDto deleteProduct(@RequestParam Long cartId) {
        return new ProductDto(3L,"Deleted product","Deleted description",100.100,10L);
    }

    @RequestMapping(method = RequestMethod.GET, value = "createOrder")
    public OrderDto createOrder() {
        return new OrderDto(1L,"New order");
    }
}
