package com.example.librarymanagement.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.librarymanagement.dto.BookAuthorView;
import com.example.librarymanagement.entity.Author;
import com.example.librarymanagement.entity.Book;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void findBooksWithAuthorsReturnsInnerJoinRows() {
        Author author = authorRepository.save(new Author("Test Author", "test.author@example.com", "India"));
        bookRepository.save(new Book("Test Book", "9780000000999", "Education", 2024, author));

        List<BookAuthorView> rows = bookRepository.findBooksWithAuthors();

        assertThat(rows).hasSize(1);
        assertThat(rows.get(0).title()).isEqualTo("Test Book");
        assertThat(rows.get(0).authorName()).isEqualTo("Test Author");
    }

    @Test
    void existsByIsbnDetectsStoredBook() {
        Author author = authorRepository.save(new Author("ISBN Author", "isbn.author@example.com", "USA"));
        bookRepository.save(new Book("ISBN Book", "9780000000888", "Technology", 2023, author));

        assertThat(bookRepository.existsByIsbn("9780000000888")).isTrue();
    }
}
