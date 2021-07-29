package com.phuongtd.book.repositories;

import com.phuongtd.book.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAllByOrderByTitle();

    List<Book> findAllByOrderByAuthor();

    List<Book> findAllByOrderByCreatedAt();

    @Query(
            value = "SELECT * FROM Book b WHERE b.title LIKE CONCAT('%',?1,'%') or b.author LIKE CONCAT('%',?1,'%')",
            nativeQuery = true)
    List<Book> findByTitleOrAuthor(String keyword);

    Book findByTitle(String title);
}
