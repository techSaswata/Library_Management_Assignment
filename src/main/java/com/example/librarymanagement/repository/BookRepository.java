package com.example.librarymanagement.repository;

import com.example.librarymanagement.dto.BookAuthorView;
import com.example.librarymanagement.entity.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByIsbn(String isbn);

    @Query("""
            select new com.example.librarymanagement.dto.BookAuthorView(
                b.id,
                b.title,
                b.isbn,
                b.genre,
                b.publishedYear,
                a.id,
                a.name,
                a.email,
                a.country
            )
            from Book b
            inner join b.author a
            order by a.name, b.title
            """)
    List<BookAuthorView> findBooksWithAuthors();
}
