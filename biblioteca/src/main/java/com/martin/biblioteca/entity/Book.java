package com.martin.biblioteca.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Book {
    @Id
    private Long isbn;

    private String title;
    private Integer quantity;

    @Temporal(TemporalType.DATE)
    private LocalDate publicationDate;

    @ManyToOne
    private Author author;

    @ManyToOne
    private Editorial editorial;
}
