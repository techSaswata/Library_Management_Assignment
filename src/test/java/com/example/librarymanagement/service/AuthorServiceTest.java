package com.example.librarymanagement.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.librarymanagement.entity.Author;
import com.example.librarymanagement.repository.AuthorRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @Test
    void createRejectsDuplicateEmail() {
        Author author = new Author("Duplicate", "duplicate@example.com", "India");
        when(authorRepository.existsByEmail("duplicate@example.com")).thenReturn(true);

        assertThatThrownBy(() -> authorService.create(author))
                .isInstanceOf(DataIntegrityViolationException.class)
                .hasMessageContaining("email");
    }

    @Test
    void updateChangesExistingAuthorDetails() {
        Author existing = new Author("Old Name", "old@example.com", "India");
        existing.setId(1L);
        Author changes = new Author("New Name", "new@example.com", "USA");
        when(authorRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(authorRepository.save(existing)).thenReturn(existing);

        Author updated = authorService.update(1L, changes);

        assertThat(updated.getName()).isEqualTo("New Name");
        assertThat(updated.getEmail()).isEqualTo("new@example.com");
        assertThat(updated.getCountry()).isEqualTo("USA");
        verify(authorRepository).save(existing);
    }
}
