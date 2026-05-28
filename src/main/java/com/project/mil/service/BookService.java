package com.project.mil.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.mil.domain.book.Book;
import com.project.mil.domain.book.BookFilter;
import com.project.mil.domain.book.BookRequestDTO;
import com.project.mil.repository.book.BookRepository;

@Service
public class BookService {
    
    BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void postBook(Book book) {
        bookRepository.save(book);
    }

    public List<Book> getBooks(BookFilter filter) {
        return bookRepository.findBooksQuery(filter.title(), filter.authorName(), filter.publishYear(), filter.synopsis(), filter.isbn());
    }

    public Book updateBook(Long id, BookRequestDTO book) {
        Book bookToUpdate = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.title() != null) {
            bookToUpdate.setTitle(book.title());
        }

        if (book.authorName() != null) {
            bookToUpdate.setAuthorName(book.authorName());
        }

        if (book.publishYear() != null) {
            bookToUpdate.setPublishYear(book.publishYear());
        }

        if (book.synopsis() != null) {
            bookToUpdate.setSynopsis(book.synopsis());
        }

        if (book.isbn() != null) {
            bookToUpdate.setIsbn(book.isbn());
        }

        bookRepository.save(bookToUpdate);
        return bookToUpdate;
    }
}
