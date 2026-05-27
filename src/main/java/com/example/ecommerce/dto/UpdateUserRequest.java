package com.example.ecommerce.dto;

import com.example.ecommerce.enums.Role;
import lombok.Data;

@Data
public class UpdateUserRequest {

    private String username;

    private String email;

    private Role role;
}