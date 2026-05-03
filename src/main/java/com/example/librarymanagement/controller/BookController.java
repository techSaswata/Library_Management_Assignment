package com.example.librarymanagement.controller;

import com.example.librarymanagement.entity.Author;
import com.example.librarymanagement.entity.Book;
import com.example.librarymanagement.service.AuthorService;
import com.example.librarymanagement.service.BookService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("bookAuthorRows", bookService.findBooksWithAuthors());
        return "books/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        Book book = new Book();
        book.setAuthor(new Author());
        model.addAttribute("book", book);
        prepareBookForm(model, "Add Book", "/books");
        return "books/form";
    }

    @PostMapping
    public String createBook(@Valid @ModelAttribute("book") Book book,
                             BindingResult bindingResult,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            prepareBookForm(model, "Add Book", "/books");
            return "books/form";
        }
        try {
            bookService.create(book);
            redirectAttributes.addFlashAttribute("successMessage", "Book added successfully.");
            return "redirect:/books";
        } catch (DataIntegrityViolationException ex) {
            bindingResult.reject("integrity", ex.getMostSpecificCause().getMessage());
            prepareBookForm(model, "Add Book", "/books");
            return "books/form";
        }
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        prepareBookForm(model, "Update Book", "/books/" + id);
        return "books/form";
    }

    @PostMapping("/{id}")
    public String updateBook(@PathVariable Long id,
                             @Valid @ModelAttribute("book") Book book,
                             BindingResult bindingResult,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            prepareBookForm(model, "Update Book", "/books/" + id);
            return "books/form";
        }
        try {
            bookService.update(id, book);
            redirectAttributes.addFlashAttribute("successMessage", "Book updated successfully.");
            return "redirect:/books";
        } catch (DataIntegrityViolationException ex) {
            bindingResult.reject("integrity", "ISBN must be unique and author must be selected.");
            prepareBookForm(model, "Update Book", "/books/" + id);
            return "books/form";
        }
    }

    private void prepareBookForm(Model model, String formTitle, String actionUrl) {
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("formTitle", formTitle);
        model.addAttribute("actionUrl", actionUrl);
    }
}
