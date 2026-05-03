package com.example.librarymanagement.controller;

import com.example.librarymanagement.entity.Author;
import com.example.librarymanagement.service.AuthorService;
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
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String listAuthors(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "authors/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("author", new Author());
        model.addAttribute("formTitle", "Add Author");
        model.addAttribute("actionUrl", "/authors");
        return "authors/form";
    }

    @PostMapping
    public String createAuthor(@Valid @ModelAttribute("author") Author author,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            prepareAuthorForm(model, "Add Author", "/authors");
            return "authors/form";
        }
        try {
            authorService.create(author);
            redirectAttributes.addFlashAttribute("successMessage", "Author added successfully.");
            return "redirect:/authors";
        } catch (DataIntegrityViolationException ex) {
            bindingResult.reject("duplicate", ex.getMostSpecificCause().getMessage());
            prepareAuthorForm(model, "Add Author", "/authors");
            return "authors/form";
        }
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        model.addAttribute("author", authorService.findById(id));
        model.addAttribute("formTitle", "Update Author");
        model.addAttribute("actionUrl", "/authors/" + id);
        return "authors/form";
    }

    @PostMapping("/{id}")
    public String updateAuthor(@PathVariable Long id,
                               @Valid @ModelAttribute("author") Author author,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            prepareAuthorForm(model, "Update Author", "/authors/" + id);
            return "authors/form";
        }
        try {
            authorService.update(id, author);
            redirectAttributes.addFlashAttribute("successMessage", "Author updated successfully.");
            return "redirect:/authors";
        } catch (DataIntegrityViolationException ex) {
            bindingResult.reject("duplicate", "Author name or email must be unique.");
            prepareAuthorForm(model, "Update Author", "/authors/" + id);
            return "authors/form";
        }
    }

    private void prepareAuthorForm(Model model, String formTitle, String actionUrl) {
        model.addAttribute("formTitle", formTitle);
        model.addAttribute("actionUrl", actionUrl);
    }
}
