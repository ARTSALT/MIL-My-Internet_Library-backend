package com.project.mil.domain;

import com.project.mil.domain.book.Book;
import com.project.mil.domain.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "user_books")
@Table(name = "user_books")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserBooks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    //TODO Fazer Enum Review 0-5 estrelas
    private Integer rating;

    //TODO Fazer Enum Status
    private Integer Status;

    private String Comment;
}
