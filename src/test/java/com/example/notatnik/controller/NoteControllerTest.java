package com.example.notatnik.controller;

import com.example.notatnik.entity.AppUser;
import com.example.notatnik.entity.Note;
import com.example.notatnik.service.AppUserService;
import com.example.notatnik.service.NoteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NoteController.class)
class NoteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    NoteService noteService;

    @MockBean
    AppUserService appUserService;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void shouldReturnStatusOkForGetRequest() throws Exception {
        AppUser user = new AppUser();
        user.setId(1L);
        user.setUsername("user");

        Note note = new Note();
        note.setContent("Notatka testowa");
        note.setAppUser(user);

        Mockito.when(appUserService.findByUsername(any())).thenReturn(Optional.of(user));
        Mockito.when(noteService.getNotesByUser(1L)).thenReturn(List.of(note));

        mockMvc.perform(get("/api/notes").principal(() -> "user"))
                .andExpect(status().isOk());
    }
}