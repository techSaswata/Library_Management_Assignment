package com.example.librarymanagement.service;

import com.example.librarymanagement.dto.BookAuthorView;
import com.example.librarymanagement.entity.Author;
import com.example.librarymanagement.entity.Book;
import com.example.librarymanagement.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookService(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id " + id));
    }

    @Transactional(readOnly = true)
    public List<BookAuthorView> findBooksWithAuthors() {
        return bookRepository.findBooksWithAuthors();
    }

    public Book create(Book book) {
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new DataIntegrityViolationException("A book with this ISBN already exists.");
        }
        Long authorId = book.getAuthor() == null ? null : book.getAuthor().getId();
        if (authorId == null) {
            throw new DataIntegrityViolationException("Please select an author for the book.");
        }
        Author author = authorService.findById(authorId);
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    public Book update(Long id, Book bookDetails) {
        Book book = findById(id);
        Long authorId = bookDetails.getAuthor() == null ? null : bookDetails.getAuthor().getId();
        if (authorId == null) {
            throw new DataIntegrityViolationException("Please select an author for the book.");
        }
        Author author = authorService.findById(authorId);
        book.setTitle(bookDetails.getTitle());
        book.setIsbn(bookDetails.getIsbn());
        book.setGenre(bookDetails.getGenre());
        book.setPublishedYear(bookDetails.getPublishedYear());
        book.setAuthor(author);
        return bookRepository.save(book);
    }
}
