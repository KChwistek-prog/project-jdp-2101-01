package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Order;
import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface OrderRepository extends CrudRepository<Order, Long> {
    @Override
    List<Order> findAll();
}
