package com.project.mil.repository.book;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.mil.domain.book.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    Optional<Book> findBookByIsbn(String isbn);

    @Query("""
        SELECT b from Book b WHERE 
        (:title is null or LOWER(b.title) LIKE LOWER(CONCAT('%', CAST(:title AS string), '%'))) and 
        (:authorName is null or LOWER(b.authorName) LIKE LOWER(CONCAT('%', CAST(:authorName AS string), '%'))) and 
        (:publishYear is null or b.publishYear = :publishYear) and
        (:synopsis is null or LOWER(b.synopsis) LIKE LOWER(CONCAT('%', CAST(:synopsis AS string), '%'))) and
        (:isbn is null or LOWER(b.isbn) LIKE LOWER(CONCAT('%', CAST(:isbn AS string), '%')))
        """
    )
    List<Book> findBooksQuery(
        @Param("title") String title,
        @Param("authorName") String authorName,
        @Param("publishYear") Integer publishYear,  
        @Param("synopsis") String synopsis,
        @Param("isbn") String isbn
    );
}
