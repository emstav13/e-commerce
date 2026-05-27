package com.example.ecommerce.controllers;

import com.example.ecommerce.entities.Product;
import com.example.ecommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public Page<Product> getAllProducts(

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size
    ) {

        return productRepository.findAll(
                PageRequest.of(page, size)
        );
    }

    @GetMapping("/{id}")
    public Product getProductById(
            @PathVariable Long id
    ) {

        return productRepository.findById(id)
                .orElse(null);
    }

    @GetMapping("/search")
    public Object searchProducts(

            @RequestParam String name
    ) {

        return productRepository
                .findByNameContaining(name);
    }
}