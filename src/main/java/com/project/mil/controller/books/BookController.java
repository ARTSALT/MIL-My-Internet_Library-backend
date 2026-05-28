package com.project.mil.controller.books;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.mil.domain.book.Book;
import com.project.mil.domain.book.BookFilter;
import com.project.mil.domain.book.BookRequestDTO;
import com.project.mil.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody BookRequestDTO request) {

        Book book = new Book(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getBooks(BookFilter filter) {
        // Teóricamente o filtro deveria ser pego a partir de query params
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBooks(filter));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody BookRequestDTO request) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.updateBook(id, request));
    }
    
}
