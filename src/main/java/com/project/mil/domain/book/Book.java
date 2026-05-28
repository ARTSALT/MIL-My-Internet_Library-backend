package com.project.mil.domain.book;

import jakarta.persistence.Column;
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

@Entity
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

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String authorName;

    @Column(name = "publish_year")
    private Integer publishYear;

    @Column(name = "synopsis")
    private String synopsis;

    @Column(name = "isbn")
    private String isbn;

    public Book(BookRequestDTO book) {
        this.title = book.title();
        this.authorName = book.authorName();
        this.publishYear = book.publishYear();
        this.synopsis = book.synopsis();
        this.isbn = book.isbn();
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", authorName=" + authorName + ", publishYear=" + publishYear
                + ", synopsis=" + synopsis + ", isbn=" + isbn + "]";
    }
}
