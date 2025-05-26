package com.example.notatnik.service;

import com.example.notatnik.entity.AppUser;

import java.util.Optional;

public interface AppUserService {
    Optional<AppUser> findByUsername(String username);
    AppUser save(AppUser user);
}