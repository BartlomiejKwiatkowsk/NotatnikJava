package com.example.notatnik.dto;

import lombok.Data;

@Data
public class AppUserDto {
    private Long id;
    private String username;
    private String role;
}