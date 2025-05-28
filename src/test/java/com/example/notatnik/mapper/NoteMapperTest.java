package com.example.notatnik.mapper;

import com.example.notatnik.dto.NoteDto;
import com.example.notatnik.entity.Note;
import com.example.notatnik.entity.AppUser;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class NoteMapperTest {

    @Test
    void shouldMapNoteToDto() {
        AppUser user = new AppUser();
        user.setId(5L);

        Note n = new Note();
        n.setId(7L);
        n.setContent("test-content");
        n.setCreatedAt(LocalDateTime.now());
        n.setAppUser(user);

        NoteDto dto = NoteMapper.toDto(n);

        assertThat(dto.getId()).isEqualTo(n.getId());
        assertThat(dto.getContent()).isEqualTo(n.getContent());
        assertThat(dto.getUserId()).isEqualTo(user.getId());
    }
}