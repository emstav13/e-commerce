package com.example.ecommerce.controllers;

import com.example.ecommerce.entities.Order;
import com.example.ecommerce.entities.Product;
import com.example.ecommerce.entities.User;
import com.example.ecommerce.enums.OrderStatus;
import com.example.ecommerce.repositories.OrderRepository;
import com.example.ecommerce.repositories.ProductRepository;
import com.example.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public Order createOrder(

            @RequestParam Long productId,

            @RequestParam Integer quantity,

            Authentication authentication
    ) {

        User user =
                (User) authentication.getPrincipal();

        Product product =
                productRepository.findById(productId)
                        .orElse(null);

        if(product == null) {
            return null;
        }

        Order order = new Order();

        order.setUser(user);

        order.setProduct(product);

        order.setQuantity(quantity);

        order.setOrderDate(LocalDateTime.now());

        order.setStatus(OrderStatus.PENDING);

        order.setTotalAmount(
                product.getPrice() * quantity
        );

        return orderRepository.save(order);
    }

    @GetMapping("/my-orders")
    public List<Order> myOrders(
            Authentication authentication
    ) {

        User user =
                (User) authentication.getPrincipal();

        return orderRepository.findByUser(user);
    }
}