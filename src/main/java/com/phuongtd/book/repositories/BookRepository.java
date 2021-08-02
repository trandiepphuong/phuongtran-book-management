package com.phuongtd.book.repositories;

import com.phuongtd.book.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {


    @Query(
            value = "SELECT * FROM Book b WHERE b.title LIKE CONCAT('%',?1,'%') or b.author LIKE CONCAT('%',?1,'%')",
            nativeQuery = true)
    List<Book> findByTitleOrAuthor(String keyword);

    @Query(
            value = "SELECT * FROM Book b WHERE b.title LIKE CONCAT('%',?1,'%') or b.author LIKE CONCAT('%',?1,'%') ORDER BY b.title",
            nativeQuery = true)
    List<Book> findByTitleOrAuthorByOrderByTitle(String keyword);
    @Query(
            value = "SELECT * FROM Book b WHERE b.title LIKE CONCAT('%',?1,'%') or b.author LIKE CONCAT('%',?1,'%') ORDER BY b.author",
            nativeQuery = true)
    List<Book> findByTitleOrAuthorByOrderByAuthor(String keyword);
    @Query(
            value = "SELECT * FROM Book b WHERE b.title LIKE CONCAT('%',?1,'%') or b.author LIKE CONCAT('%',?1,'%') ORDER BY b.createdAt",
            nativeQuery = true)
    List<Book> findByTitleOrAuthorByOrderByCreatedAt(String keyword);
}
