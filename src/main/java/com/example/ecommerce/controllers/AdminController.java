package com.example.ecommerce.controllers;

import com.example.ecommerce.dto.UpdateUserRequest;
import com.example.ecommerce.entities.Product;
import com.example.ecommerce.entities.User;
import com.example.ecommerce.repositories.ProductRepository;
import com.example.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/products")
    public Product createProduct(
            @RequestBody Product product
    ) {

        return productRepository.save(product);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(

            @PathVariable Long id,

            @RequestBody Product product
    ) {

        Product existingProduct =
                productRepository.findById(id)
                        .orElse(null);

        if (existingProduct == null) {
            return null;
        }

        existingProduct.setName(product.getName());

        existingProduct.setDescription(
                product.getDescription()
        );

        existingProduct.setPrice(product.getPrice());

        existingProduct.setStockQuantity(
                product.getStockQuantity()
        );

        existingProduct.setLienImage(
                product.getLienImage()
        );

        existingProduct.setCategory(
                product.getCategory()
        );

        return productRepository.save(existingProduct);
    }

    @DeleteMapping("/products/{id}")
    public String deleteProduct(
            @PathVariable Long id
    ) {

        productRepository.deleteById(id);

        return "Product deleted";
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @PutMapping("/users/{id}")
    public User updateUser(

            @PathVariable Long id,

            @RequestBody UpdateUserRequest request
    ) {

        User user =
                userRepository.findById(id)
                        .orElse(null);

        if (user == null) {
            return null;
        }

        user.setUsername(request.getUsername());

        user.setEmail(request.getEmail());

        user.setRole(request.getRole());

        return userRepository.save(user);
    }
}