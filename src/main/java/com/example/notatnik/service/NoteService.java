package com.example.notatnik.service;

import com.example.notatnik.entity.Note;
import java.util.List;
import java.util.Optional;

public interface NoteService {
    Note addNote(Note note, Long userId);
    List<Note> getNotesByUser(Long userId);
    Optional<Note> getNoteById(Long noteId);
    void deleteNote(Long noteId, Long userId);
}