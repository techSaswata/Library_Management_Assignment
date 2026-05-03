package com.example.librarymanagement.repository;

import com.example.librarymanagement.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    boolean existsByEmail(String email);

    boolean existsByName(String name);
}
