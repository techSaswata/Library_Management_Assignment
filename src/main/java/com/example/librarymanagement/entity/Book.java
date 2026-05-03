package com.example.librarymanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Book title is required")
    @Size(max = 150, message = "Title must be under 150 characters")
    @Column(nullable = false, length = 150)
    private String title;

    @NotBlank(message = "ISBN is required")
    @Size(max = 20, message = "ISBN must be under 20 characters")
    @Column(nullable = false, unique = true, length = 20)
    private String isbn;

    @NotBlank(message = "Genre is required")
    @Column(nullable = false, length = 80)
    private String genre;

    @NotNull(message = "Published year is required")
    @Min(value = 1000, message = "Enter a valid year")
    @Max(value = 2100, message = "Enter a valid year")
    @Column(nullable = false)
    private Integer publishedYear;

    @NotNull(message = "Author is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    public Book() {
    }

    public Book(String title, String isbn, String genre, Integer publishedYear, Author author) {
        this.title = title;
        this.isbn = isbn;
        this.genre = genre;
        this.publishedYear = publishedYear;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
