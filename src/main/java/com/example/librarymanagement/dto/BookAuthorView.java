package com.example.librarymanagement.dto;

public record BookAuthorView(
        Long bookId,
        String title,
        String isbn,
        String genre,
        Integer publishedYear,
        Long authorId,
        String authorName,
        String authorEmail,
        String country
) {
}
