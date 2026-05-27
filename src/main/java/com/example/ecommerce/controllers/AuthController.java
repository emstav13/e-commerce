package com.example.ecommerce.controllers;

import com.example.ecommerce.dto.LoginRequest;
import com.example.ecommerce.dto.RegisterRequest;
import com.example.ecommerce.entities.User;
import com.example.ecommerce.enums.Role;
import com.example.ecommerce.repositories.UserRepository;
import com.example.ecommerce.security.JwtTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request
    ) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {

            return ResponseEntity.badRequest()
                    .body("Email already exists");
        }

        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        String encodedPassword =
                passwordEncoder.encode(request.getPassword());

        user.setPassword(encodedPassword);

        user.setRole(Role.USER);

        userRepository.save(user);

        return ResponseEntity.ok("User registered");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request
    ) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElse(null);

        if (user == null) {

            return ResponseEntity.badRequest()
                    .body("User not found");
        }

        System.out.println("REQUEST PASSWORD: " + request.getPassword());
        System.out.println("DATABASE PASSWORD: " + user.getPassword());

        boolean validPassword =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );

        System.out.println("PASSWORD MATCH: " + validPassword);

        if (!validPassword) {

            return ResponseEntity.badRequest()
                    .body("Invalid password");
        }

        String token =
                JwtTokenManager.generateToken(user.getEmail());

        return ResponseEntity.ok(token);
    }
}