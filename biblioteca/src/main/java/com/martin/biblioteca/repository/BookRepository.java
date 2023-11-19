package com.martin.biblioteca.repository;

import com.martin.biblioteca.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // use this method to find a book by title using Jpa naming convention
    //  Book findBookByTitle(String title);

    // use this method to find a book by title using the query annotation
    // my creation
    /*
    @Query("SELECT b FROM Book b WHERE b.title = ?1")
    Book searchBookByTitle(String title);

     */
    // egg creation
    @Query("SELECT b FROM Book b WHERE b.title = :title")
    Book searchBookByTitleEgg(@Param("title") String title);



}
