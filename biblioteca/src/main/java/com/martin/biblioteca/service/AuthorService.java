package com.martin.biblioteca.service;

import com.martin.biblioteca.entity.Author;
import com.martin.biblioteca.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    @Transactional(readOnly = true)
    public Optional<Author> getAuthorById(String authorId) {
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        return authorOptional;

    }
    @Transactional
    public Author createAuthor(Author author) {
        Author authorSaved = authorRepository.save(author);
        if(authorSaved == null) {
            throw new RuntimeException("Author not saved");
        }
        return authorSaved;
    }
    @Transactional
    public Author createAuthor(String name) {
        Author author = new Author();
        author.setName(name);
        Author authorSaved = authorRepository.save(author);
        if(authorSaved == null) {
            throw new RuntimeException("Author not saved");
        }
        return authorSaved;
    }
    @Transactional(readOnly = true)
    public List<Author> getAllAuthors() {
        List<Author> authorList = authorRepository.findAll();
        return authorList;
    }
}