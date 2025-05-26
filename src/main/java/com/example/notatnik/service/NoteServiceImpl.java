package com.example.notatnik.service;

import com.example.notatnik.entity.Note;
import com.example.notatnik.entity.AppUser;
import com.example.notatnik.repository.NoteRepository;
import com.example.notatnik.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final AppUserRepository appUserRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository, AppUserRepository appUserRepository) {
        this.noteRepository = noteRepository;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public Note addNote(Note note, Long userId) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono użytkownika"));
        note.setAppUser(user);
        return noteRepository.save(note);
    }

    @Override
    public List<Note> getNotesByUser(Long userId) {
        return noteRepository.findByAppUser_Id(userId);
    }

    @Override
    public Optional<Note> getNoteById(Long noteId) {
        return noteRepository.findById(noteId);
    }

    @Override
    public void deleteNote(Long noteId, Long userId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Brak notatki"));
        if (!note.getAppUser().getId().equals(userId)) {
            throw new RuntimeException("Nie masz uprawnień do usunięcia tej notatki");
        }
        noteRepository.delete(note);
    }
    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    public void deleteAnyNote(Long noteId) {
        noteRepository.deleteById(noteId);
    }
}