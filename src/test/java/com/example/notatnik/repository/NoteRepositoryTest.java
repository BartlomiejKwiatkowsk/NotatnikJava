package com.example.notatnik.repository;

import com.example.notatnik.entity.AppUser;
import com.example.notatnik.entity.Note;
import com.example.notatnik.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class NoteRepositoryTest {

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    RoleRepository roleRepository;

    @Test
    void shouldSaveAndFindNoteByUser() {
        // Tworzymy i zapisujemy rolę
        Role role = new Role();
        role.setName("USER");
        role = roleRepository.save(role);

        // Tworzymy i zapisujemy użytkownika z rolą
        AppUser user = new AppUser();
        user.setUsername("testuser");
        user.setPassword("testpass");
        user.setRole(role);
        user = appUserRepository.save(user);

        // Tworzymy i zapisujemy notatkę z użytkownikiem
        Note note = new Note();
        note.setContent("Notatka testowa");
        note.setAppUser(user);
        note = noteRepository.save(note);

        // Testujemy, czy notatka jest widoczna po userId
        List<Note> notes = noteRepository.findByAppUser_Id(user.getId());
        assertThat(notes).extracting(Note::getContent).contains("Notatka testowa");
    }
}