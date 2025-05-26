package com.example.notatnik.controller;

import com.example.notatnik.dto.NoteCreateDto;
import com.example.notatnik.dto.NoteDto;
import com.example.notatnik.entity.AppUser;
import com.example.notatnik.entity.Note;
import com.example.notatnik.mapper.NoteMapper;
import com.example.notatnik.service.AppUserService;
import com.example.notatnik.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;
    private final AppUserService appUserService;

    @Autowired
    public NoteController(NoteService noteService, AppUserService appUserService) {
        this.noteService = noteService;
        this.appUserService = appUserService;
    }

    // Zwraca notatki zalogowanego użytkownika
    @GetMapping
    public List<NoteDto> getUserNotes(Principal principal) {
        AppUser user = appUserService.findByUsername(principal.getName())
                .orElseThrow();
        return noteService.getNotesByUser(user.getId())
                .stream()
                .map(NoteMapper::toDto)
                .collect(Collectors.toList());
    }

    // Dodawanie notatki
    @PostMapping
    public NoteDto addNote(@RequestBody NoteCreateDto dto, Principal principal) {
        AppUser user = appUserService.findByUsername(principal.getName())
                .orElseThrow();
        Note note = new Note();
        note.setContent(dto.getContent());
        note.setAppUser(user);
        Note saved = noteService.addNote(note, user.getId());
        return NoteMapper.toDto(saved);
    }

    // Usuwanie notatki użytkownika (usuwa tylko swoją)
    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable Long id, Principal principal) {
        AppUser user = appUserService.findByUsername(principal.getName())
                .orElseThrow();
        noteService.deleteNote(id, user.getId());
    }
}