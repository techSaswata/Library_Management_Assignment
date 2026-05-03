# Library Management - Spring Boot JSP CRUD

This project is a Spring Boot MVC application for managing two related entities: authors and books. It satisfies the assignment requirements for create, read, and update operations, JPA relationships, repository/service/controller layers, JSP views, CSS styling, sample data, and tests.

## Entity Relationship Design

- `Author` represents writer information with `id`, `name`, `email`, and `country`.
- `Book` represents library book information with `id`, `title`, `isbn`, `genre`, and `publishedYear`.
- One author can have many books.
- Each book belongs to exactly one author through `@ManyToOne`.
- `Author` owns the reverse side with `@OneToMany(mappedBy = "author")`.

## Main Features

- Database tables are generated from JPA entities using Hibernate.
- `DataSeeder` inserts 10 authors and 10 books when the app starts.
- `/authors` lists authors and provides author update links.
- `/authors/new` creates a new author.
- `/books` lists books with author details using an inner join query.
- `/books/new` creates a new book and assigns it to an author.
- Edit pages update existing authors and books.
- Duplicate author email/name and duplicate book ISBN are handled through service checks and database constraints.

## Inner Join Query

`BookRepository.findBooksWithAuthors()` performs the required inner join and returns a DTO projection:

```java
from Book b
inner join b.author a
```

The result is displayed on the `/books` JSP page.

## Project Layers

- Entity layer: `Author`, `Book`
- Repository layer: `AuthorRepository`, `BookRepository`
- Service layer: `AuthorService`, `BookService`
- Controller layer: `AuthorController`, `BookController`, `HomeController`
- View layer: JSP pages under `src/main/webapp/WEB-INF/views`

## How To Run

```bash
mvn spring-boot:run
```

Open:

- App: `http://localhost:8080/books`
- H2 console: `http://localhost:8080/h2-console`

H2 JDBC URL:

```text
jdbc:h2:mem:librarydb
```

## Testing

Run:

```bash
mvn test
```

Tests include:

- Repository tests for custom query and ISBN lookup.
- Service tests for create/update logic and integrity handling.

## PDF Submission Notes

Use this README as the base for the required PDF document. Add screenshots from these pages after running the app:

- `/books`
- `/books/new`
- `/authors`
- `/authors/new`
- Any edit page

Add your GitHub repository URL after pushing the project.
