package com.project.mil.domain.book;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "books")
@Table(name = "books")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String authorName;

    private Integer publishYear;

    private String synopsis;

    private String ISBN;

    public Book(BookRequestDTO book) {
        this.title = book.title();
        this.authorName = book.authorName();
        this.publishYear = book.publishYear();
        this.synopsis = book.synopsis();
        this.ISBN = book.isbn();
    }
}
