package com.example.notatnik.controller;

import com.example.notatnik.dto.NoteCreateDto;
import com.example.notatnik.entity.AppUser;
import com.example.notatnik.entity.Note;
import com.example.notatnik.service.AppUserService;
import com.example.notatnik.service.NoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.security.Principal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NoteController.class)
@AutoConfigureMockMvc(addFilters = false)  // Wyłączenie security w testach kontrolera
class NoteControllerPostDeleteTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private NoteService noteService;

    @MockBean
    private AppUserService appUserService;

    // Pomocnicza funkcja do utworzenia Principal z nazwą użytkownika
    private Principal getPrincipal(String username) {
        return () -> username;
    }

    @Test
    void shouldCreateNote() throws Exception {
        AppUser user = new AppUser();
        user.setId(1L);
        user.setUsername("user");

        Note note = new Note();
        note.setContent("Nowa notatka");
        note.setAppUser(user);

        Mockito.when(appUserService.findByUsername(any())).thenReturn(Optional.of(user));
        Mockito.when(noteService.addNote(any(), any())).thenReturn(note);

        NoteCreateDto dto = new NoteCreateDto();
        dto.setContent("Nowa notatka");

        mockMvc.perform(post("/api/notes")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dto))
                        .principal(getPrincipal("user"))
                )
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteNote() throws Exception {
        AppUser user = new AppUser();
        user.setId(1L);
        user.setUsername("user");

        Mockito.when(appUserService.findByUsername(any())).thenReturn(Optional.of(user));

        mockMvc.perform(delete("/api/notes/123")
                        .principal(getPrincipal("user"))
                )
                .andExpect(status().isOk());
    }
}