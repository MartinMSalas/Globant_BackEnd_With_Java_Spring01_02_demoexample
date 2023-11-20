package com.martin.biblioteca.controller;

import com.martin.biblioteca.service.EditorialService;
import com.martin.biblioteca.service.AuthorService;
import com.martin.biblioteca.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final EditorialService editorialService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService, EditorialService editorialService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.editorialService = editorialService;
    }

    @GetMapping("/register")
    public String registerBook(ModelMap modelMap){

        return "book_form.html";
    }

    @PostMapping("/create")
    public String createBook(@RequestParam(required = false) Long isbn, @RequestParam(required = false)  String title, @RequestParam(required = false)  Integer quantity, @RequestParam(required = false)  String authorId,  @RequestParam(required = false)  String editorialId, ModelMap modelMap) {
        if (isbn == null || title == null || quantity == null || authorId == null || editorialId == null) {
            modelMap.put("error", "All fields are required");
            return "book_form.html";
        }
        System.out.println("Book isbn: " + isbn);
        System.out.println("Book title: " + title);
        System.out.println("Book quantity: " + quantity);
        System.out.println("Book authorId: " + authorId);
        System.out.println("Book editorialId: " + editorialId);
        bookService.createBook(isbn, title, quantity, authorId, editorialId);
        modelMap.put("success", "Book created successfully");
        return "index.html";
    }
}
