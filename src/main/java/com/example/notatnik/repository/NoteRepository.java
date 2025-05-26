package com.example.notatnik.repository;

import com.example.notatnik.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByAppUser_Id(Long userId);
}