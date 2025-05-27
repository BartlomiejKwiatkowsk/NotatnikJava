package com.example.notatnik.service;

import com.example.notatnik.entity.Role;
import com.example.notatnik.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class RoleServiceImplTest {

    @Test
    void shouldFindRoleByName() {
        RoleRepository roleRepository = Mockito.mock(RoleRepository.class);
        RoleServiceImpl roleService = new RoleServiceImpl(roleRepository);

        Role role = new Role();
        role.setName("ADMIN");

        Mockito.when(roleRepository.findByName("ADMIN")).thenReturn(Optional.of(role));

        Optional<Role> found = roleService.findByName("ADMIN");
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("ADMIN");
    }
}