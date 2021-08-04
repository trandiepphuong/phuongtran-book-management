package com.phuongtd.book.repositories;

import com.phuongtd.book.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Page<Book> findAllByEnabled(Pageable pageable, Boolean enabled);

    @Query( value = "SELECT * FROM Book b WHERE b.title LIKE CONCAT('%',?1,'%') OR b.author LIKE CONCAT('%',?1,'%') AND b.enabled = true",
            nativeQuery = true)
    Page<Book> findByTitleOrAuthor(String keyword, Pageable pageable);

    @Query( value = "SELECT * FROM Book b WHERE b.title LIKE CONCAT('%',?1,'%') OR b.author LIKE CONCAT('%',?1,'%') AND b.enabled = true ORDER BY b.title",
            nativeQuery = true)
    Page<Book> findByTitleOrAuthorByOrderByTitleAndByEnabled(String keyword, Pageable pageable);

    @Query( value = "SELECT * FROM Book b WHERE b.title LIKE CONCAT('%',?1,'%') OR b.author LIKE CONCAT('%',?1,'%') AND b.enabled = true ORDER BY b.author",
            nativeQuery = true)
    Page<Book> findByTitleOrAuthorByOrderByAuthor(String keyword, Pageable pageable);

    @Query( value = "SELECT * FROM Book b WHERE b.title LIKE CONCAT('%',?1,'%') OR b.author LIKE CONCAT('%',?1,'%') AND b.enabled = true ORDER BY b.createdAt",
            nativeQuery = true)
    Page<Book> findByTitleOrAuthorByOrderByCreatedAt(String keyword, Pageable pageable);
}
