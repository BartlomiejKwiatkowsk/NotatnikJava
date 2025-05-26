package com.example.notatnik.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class NoteDto {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private Long userId;
}