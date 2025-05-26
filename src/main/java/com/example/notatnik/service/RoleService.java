package com.example.notatnik.service;

import com.example.notatnik.entity.Role;
import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(String name);
}