package com.example.notatnik.service;

import com.example.notatnik.entity.AppUser;
import com.example.notatnik.entity.Note;
import com.example.notatnik.repository.AppUserRepository;
import com.example.notatnik.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class NoteServiceImplTest {

    @Test
    void shouldAddNoteForUser() {
        NoteRepository noteRepository = Mockito.mock(NoteRepository.class);
        AppUserRepository appUserRepository = Mockito.mock(AppUserRepository.class);

        NoteServiceImpl noteService = new NoteServiceImpl(noteRepository, appUserRepository);

        AppUser user = new AppUser();
        user.setId(1L);

        Note note = new Note();
        note.setContent("Nowa notatka");

        Mockito.when(appUserRepository.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(noteRepository.save(note)).thenReturn(note);

        Note savedNote = noteService.addNote(note, 1L);

        assertThat(savedNote.getContent()).isEqualTo("Nowa notatka");
    }
}