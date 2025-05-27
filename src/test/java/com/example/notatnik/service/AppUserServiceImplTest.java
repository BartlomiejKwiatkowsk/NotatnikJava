package com.example.notatnik.service;

import com.example.notatnik.entity.AppUser;
import com.example.notatnik.repository.AppUserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class AppUserServiceImplTest {

    @Test
    void shouldFindByUsername() {
        AppUserRepository appUserRepository = Mockito.mock(AppUserRepository.class);
        AppUserServiceImpl appUserService = new AppUserServiceImpl(appUserRepository);

        AppUser user = new AppUser();
        user.setUsername("mockuser");

        Mockito.when(appUserRepository.findByUsername("mockuser")).thenReturn(Optional.of(user));

        Optional<AppUser> result = appUserService.findByUsername("mockuser");
        assertThat(result).isPresent();
        assertThat(result.get().getUsername()).isEqualTo("mockuser");
    }
}