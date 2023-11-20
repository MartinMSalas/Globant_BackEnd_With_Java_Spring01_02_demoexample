package com.martin.biblioteca.service;

import com.martin.biblioteca.entity.Author;
import com.martin.biblioteca.entity.Book;
import com.martin.biblioteca.entity.Editorial;
import com.martin.biblioteca.exception.MyException;
import com.martin.biblioteca.repository.AuthorRepository;
import com.martin.biblioteca.repository.BookRepository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;
    private AuthorService authorService;
    private EditorialService editorialService;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorService authorService, EditorialService editorialService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.editorialService = editorialService;
    }
    @Transactional
    public Book createBook(Book book) {
        Book bookSaved = bookRepository.save(book);
        if(bookSaved == null) {
            throw new RuntimeException("Book not saved");
        }
        return bookSaved;

    }
    @Transactional
    public Book createBook(Long isbn, String title, Integer quantity, String authorId, String editorialId) {
        Book book = new Book();
        Optional<Author> author = authorService.getAuthorById(authorId);
        if(author.isPresent()) {
            book.setAuthor(author.get());
        }
        Optional<Editorial> editorial = editorialService.getEditorialById(editorialId);
        if(editorial.isPresent()) {
            book.setEditorial(editorial.get());
        }
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setQuantity(quantity);
        book.setPublicationDate(LocalDate.now());
        Book bookSaved = bookRepository.save(book);
        if(bookSaved == null) {
            throw new RuntimeException("Book not saved");
        }
        return bookSaved;
    }
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        List<Book> bookList = bookRepository.findAll();
        return bookList;
    }

    @Transactional
    public Book updateBook(Long isbn, String title, Integer quantity, String authorId, String editorialId) throws MyException {
        if(isbn == null) {
            throw new MyException("ISBN is required");
        }
        if(title == null) {
            throw new MyException("Title is required");
        }
        if(quantity == null) {
            throw new MyException("Quantity is required");
        }
        if(authorId == null) {
            throw new MyException("Author is required");
        }
        if(editorialId == null) {
            throw new MyException("Editorial is required");
        }
        Optional<Book> bookOptional = bookRepository.findById(isbn);
        if(bookOptional.isEmpty()) {
            throw new RuntimeException("Book not found");
        }

        Optional<Author> authorOptional = authorService.getAuthorById(authorId);
        if(authorOptional.isEmpty()) {
            throw new RuntimeException("Author not found");
        }

        Optional<Editorial> editorialOptional = editorialService.getEditorialById(editorialId);
        if(editorialOptional.isEmpty()) {
            throw new RuntimeException("Editorial not found");
        }

        Book book = bookOptional.get();
        book.setTitle(title);
        book.setQuantity(quantity);
        book.setAuthor(authorOptional.get());
        book.setEditorial(editorialOptional.get());

        Book updatedBook = bookRepository.save(book);
        return updatedBook;
    }
}
