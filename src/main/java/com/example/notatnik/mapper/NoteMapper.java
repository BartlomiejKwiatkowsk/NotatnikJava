package com.example.notatnik.mapper;

import com.example.notatnik.dto.NoteDto;
import com.example.notatnik.entity.Note;
//Klasa NoteMapper odpowiada za prostą transformację modelu bazy danych na obiekt DTO wykorzystywany w odpowiedziach REST API.
public class NoteMapper {
    public static NoteDto toDto(Note note) {
        NoteDto dto = new NoteDto();
        dto.setId(note.getId());
        dto.setContent(note.getContent());
        dto.setCreatedAt(note.getCreatedAt());
        dto.setUserId(note.getAppUser().getId());
        return dto;
    }
}