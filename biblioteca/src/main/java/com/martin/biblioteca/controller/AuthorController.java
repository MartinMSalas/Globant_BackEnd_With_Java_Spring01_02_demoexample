package com.martin.biblioteca.controller;

import com.martin.biblioteca.entity.Author;
import com.martin.biblioteca.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
    @GetMapping("/register")
    public String registerAuthor(){
        return "author_form.html";
    }

    @PostMapping("/create")
    public String createAuthor(@RequestParam String name){
        if(name == null || name.isEmpty()) {
            return "author_form.html";
        }
        System.out.println("Author name: " + name);
        Author author = authorService.createAuthor(name);
        return "index.html";

    }

}
