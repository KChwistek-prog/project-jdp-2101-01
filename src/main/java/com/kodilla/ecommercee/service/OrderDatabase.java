package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderDatabase {

    @Autowired
    private final OrderRepository repository;

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public Optional<Order> getOrder(final Long orderId){
        return repository.findById(orderId);
    }

    public Order saveOrder(final Order order){
        return repository.save(order);
    }

    public void deleteOrder(final Long orderId){
        repository.deleteById(orderId);
    }
}
