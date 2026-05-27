package com.project.mil.domain.book;

public record BookRequestDTO(String title, String authorName, Integer publishYear, String synopsis, String isbn) {
    
}
