package com.example.librarymanagement.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.librarymanagement.entity.Author;
import com.example.librarymanagement.entity.Book;
import com.example.librarymanagement.repository.BookRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private BookService bookService;

    @Test
    void createRejectsDuplicateIsbn() {
        Book book = new Book();
        book.setIsbn("9780000000111");
        when(bookRepository.existsByIsbn("9780000000111")).thenReturn(true);

        assertThatThrownBy(() -> bookService.create(book))
                .isInstanceOf(DataIntegrityViolationException.class)
                .hasMessageContaining("ISBN");
    }

    @Test
    void updateChangesExistingBookAndAuthor() {
        Author oldAuthor = new Author("Old Author", "old.author@example.com", "India");
        oldAuthor.setId(1L);
        Author newAuthor = new Author("New Author", "new.author@example.com", "USA");
        newAuthor.setId(2L);
        Book existing = new Book("Old Book", "9780000000222", "Drama", 2020, oldAuthor);
        existing.setId(10L);
        Book changes = new Book("New Book", "9780000000333", "Technology", 2024, newAuthor);
        when(bookRepository.findById(10L)).thenReturn(Optional.of(existing));
        when(authorService.findById(2L)).thenReturn(newAuthor);
        when(bookRepository.save(existing)).thenReturn(existing);

        Book updated = bookService.update(10L, changes);

        assertThat(updated.getTitle()).isEqualTo("New Book");
        assertThat(updated.getIsbn()).isEqualTo("9780000000333");
        assertThat(updated.getAuthor()).isEqualTo(newAuthor);
        verify(bookRepository).save(existing);
    }
}
