# Library Management System

Spring Boot MVC project submission for managing two related entities: `Author` and `Book`. The application implements create, read, and update operations using JSP views, Spring MVC controllers, service classes, Spring Data JPA repositories, and an H2 database.

## Tech Stack

- Spring Boot 3.3.5
- Spring MVC, JSP, JSTL
- Spring Data JPA, Hibernate
- H2 Database
- Jakarta Bean Validation
- JUnit 5, Mockito
- Maven

## Entity Design

```text
Author 1 ---- * Book
```

- `Author`: `id`, `name`, `email`, `country`
- `Book`: `id`, `title`, `isbn`, `genre`, `publishedYear`, `author`
- `Author` uses `@OneToMany`
- `Book` uses `@ManyToOne`
- `books.author_id` references `authors.id`

## Implemented Scope

- JPA-generated database tables.
- Startup seed data with 10 authors and 10 books.
- Add, list, and update authors.
- Add, list, and update books.
- Book list displays author details using a JPQL inner join DTO projection.
- Duplicate author name/email and duplicate book ISBN handling.
- Styled JSP pages for forms, tables, and status messages.
- Repository and service unit tests.

## Inner Join

Implemented in `BookRepository.findBooksWithAuthors()`:

```java
from Book b
inner join b.author a
```

The query returns `BookAuthorView` records for the `/books` page.

## Main Routes

- `/books` - list books with author details
- `/books/new` - add book form
- `/books/{id}/edit` - update book form
- `/authors` - list authors
- `/authors/new` - add author form
- `/authors/{id}/edit` - update author form
- `/h2-console` - H2 database console

## Project Structure

```text
src/main/java/com/example/librarymanagement
├── config        # sample data seeding
├── controller    # Spring MVC controllers
├── dto           # join query projection
├── entity        # JPA entities
├── repository    # JpaRepository interfaces
└── service       # business logic

src/main/webapp/WEB-INF/views
├── authors       # author JSP pages
└── books         # book JSP pages
```

## Project Report

[Check-Here](Library_Management_Project_Report.pdf)