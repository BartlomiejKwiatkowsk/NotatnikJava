package com.example.notatnik.repository;

import com.example.notatnik.entity.AppUser;
import com.example.notatnik.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AppUserRepositoryTest {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    RoleRepository roleRepository;

    @Test
    void shouldFindByUsername() {
        Role role = new Role();
        role.setName("USER");
        role = roleRepository.save(role);

        AppUser user = new AppUser();
        user.setUsername("test");
        user.setPassword("pass");
        user.setRole(role);
        appUserRepository.save(user);

        Optional<AppUser> found = appUserRepository.findByUsername("test");
        assertThat(found).isPresent();
    }
}