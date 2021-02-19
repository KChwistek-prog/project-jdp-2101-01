package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Repository
@Service
public interface ProductRepository extends CrudRepository<Product, Long> {
    @Override
    List<Product> findAll();
}
