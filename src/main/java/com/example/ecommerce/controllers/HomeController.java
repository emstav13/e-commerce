package com.example.ecommerce.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {

        return """
                <h1>Welcome to Ecommerce API</h1>
                <img src='https://images.unsplash.com/photo-1523275335684-37898b6baf30'
                width='400'/>
                """;
    }
}