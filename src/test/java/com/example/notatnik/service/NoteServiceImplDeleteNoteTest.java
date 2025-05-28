package com.example.notatnik.service;

import com.example.notatnik.entity.AppUser;
import com.example.notatnik.entity.Note;
import com.example.notatnik.repository.NoteRepository;
import com.example.notatnik.repository.AppUserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NoteServiceImplDeleteNoteTest {

    @Test
    void shouldDeleteUserNote() {
        NoteRepository noteRepository = Mockito.mock(NoteRepository.class);
        AppUserRepository appUserRepository = Mockito.mock(AppUserRepository.class);

        NoteServiceImpl noteService = new NoteServiceImpl(noteRepository, appUserRepository);

        AppUser user = new AppUser();
        user.setId(1L);

        Note note = new Note();
        note.setId(2L);
        note.setAppUser(user);

        Mockito.when(noteRepository.findById(2L)).thenReturn(Optional.of(note));

        noteService.deleteNote(2L, 1L);

        Mockito.verify(noteRepository).delete(note);

        AppUser otherUser = new AppUser();
        otherUser.setId(3L);
        note.setAppUser(otherUser);

        Mockito.when(noteRepository.findById(2L)).thenReturn(Optional.of(note));

        assertThatThrownBy(() -> noteService.deleteNote(2L, 1L))
                .isInstanceOf(RuntimeException.class);
    }
}