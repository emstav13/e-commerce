package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.Order;
import com.example.ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository
        extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);
}