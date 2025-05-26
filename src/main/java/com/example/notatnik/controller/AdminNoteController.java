package com.example.notatnik.controller;

import com.example.notatnik.dto.NoteDto;
import com.example.notatnik.entity.Note;
import com.example.notatnik.mapper.NoteMapper;
import com.example.notatnik.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/notes")
public class AdminNoteController {
    //Żeby admin miał endpointy do pobierania/usuwania każdej notatki.
    private final NoteService noteService;

    public AdminNoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public List<NoteDto> getAllNotes() {
        return noteService.getAllNotes()
                .stream().map(NoteMapper::toDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void deleteAnyNote(@PathVariable Long id) {
        noteService.deleteAnyNote(id);
    }
}