package com.example.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 2000)
    private String description;

    private Double price;

    private Integer stockQuantity;

    private String lienImage;

    private LocalDateTime createdAt =
            LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}