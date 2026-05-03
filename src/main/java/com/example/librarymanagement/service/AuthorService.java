package com.example.librarymanagement.service;

import com.example.librarymanagement.entity.Author;
import com.example.librarymanagement.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional(readOnly = true)
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Author findById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with id " + id));
    }

    public Author create(Author author) {
        if (authorRepository.existsByEmail(author.getEmail())) {
            throw new DataIntegrityViolationException("An author with this email already exists.");
        }
        if (authorRepository.existsByName(author.getName())) {
            throw new DataIntegrityViolationException("An author with this name already exists.");
        }
        return authorRepository.save(author);
    }

    public Author update(Long id, Author authorDetails) {
        Author author = findById(id);
        author.setName(authorDetails.getName());
        author.setEmail(authorDetails.getEmail());
        author.setCountry(authorDetails.getCountry());
        return authorRepository.save(author);
    }
}
