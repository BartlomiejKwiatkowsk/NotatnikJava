package com.example.notatnik.repository;

import com.example.notatnik.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RoleRepositoryTest {

    @Autowired
    RoleRepository roleRepository;

    @Test
    void shouldFindByName() {
        Role role = new Role();
        role.setName("ADMIN");
        roleRepository.save(role);

        Optional<Role> found = roleRepository.findByName("ADMIN");
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("ADMIN");
    }
}