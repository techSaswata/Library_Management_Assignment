package com.example.librarymanagement.config;

import com.example.librarymanagement.entity.Author;
import com.example.librarymanagement.entity.Book;
import com.example.librarymanagement.repository.AuthorRepository;
import com.example.librarymanagement.repository.BookRepository;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public DataSeeder(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) {
        if (authorRepository.count() > 0) {
            return;
        }

        List<Author> authors = authorRepository.saveAll(List.of(
                new Author("Aarav Mehta", "aarav.mehta@example.com", "India"),
                new Author("Maya Iyer", "maya.iyer@example.com", "India"),
                new Author("John Carter", "john.carter@example.com", "USA"),
                new Author("Emily Stone", "emily.stone@example.com", "UK"),
                new Author("Carlos Rivera", "carlos.rivera@example.com", "Spain"),
                new Author("Sakura Tanaka", "sakura.tanaka@example.com", "Japan"),
                new Author("Liam Brooks", "liam.brooks@example.com", "Canada"),
                new Author("Nora Schmidt", "nora.schmidt@example.com", "Germany"),
                new Author("Fatima Khan", "fatima.khan@example.com", "UAE"),
                new Author("Olivia Martin", "olivia.martin@example.com", "France")
        ));

        bookRepository.saveAll(List.of(
                new Book("Spring in Action", "9781617294945", "Technology", 2022, authors.get(0)),
                new Book("Clean Code Notes", "9780132350884", "Programming", 2020, authors.get(1)),
                new Book("Cloud Patterns", "9781492050285", "Technology", 2021, authors.get(2)),
                new Book("The Silent Library", "9780000000004", "Mystery", 2019, authors.get(3)),
                new Book("River of Pages", "9780000000005", "Drama", 2018, authors.get(4)),
                new Book("Tokyo Algorithms", "9780000000006", "Education", 2023, authors.get(5)),
                new Book("Northern Lights", "9780000000007", "Fiction", 2017, authors.get(6)),
                new Book("Data Stories", "9780000000008", "Analytics", 2024, authors.get(7)),
                new Book("Desert Winds", "9780000000009", "Adventure", 2020, authors.get(8)),
                new Book("Paris Letters", "9780000000010", "Romance", 2021, authors.get(9))
        ));
    }
}
