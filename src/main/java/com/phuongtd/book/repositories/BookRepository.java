package com.phuongtd.book.repositories;

import com.phuongtd.book.entities.Book;
import com.phuongtd.book.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query( value = "SELECT * FROM Book b WHERE (b.title LIKE CONCAT('%',?1,'%') OR b.author LIKE CONCAT('%',?1,'%')) AND b.enabled = 'true' ORDER BY b.title",
            nativeQuery = true)
    Page<Book> findByTitleOrAuthorByOrderByTitleAndByEnabled(String keyword, Pageable pageable);

    @Query( value = "SELECT * FROM Book b WHERE (b.title LIKE CONCAT('%',?1,'%') OR b.author LIKE CONCAT('%',?1,'%')) AND b.enabled = 'true' ORDER BY b.author",
            nativeQuery = true)
    Page<Book> findByTitleOrAuthorByOrderByAuthor(String keyword, Pageable pageable);

    @Query( value = "SELECT * FROM Book b WHERE (b.title LIKE CONCAT('%',?1,'%') OR b.author LIKE CONCAT('%',?1,'%')) AND b.enabled = 'true' ORDER BY b.created_at DESC",
            nativeQuery = true)
    Page<Book> findByTitleOrAuthorByOrderByCreatedAt(String keyword, Pageable pageable);

    @Query( value = "SELECT * FROM Book b WHERE (b.title LIKE CONCAT('%',?1,'%') OR b.author LIKE CONCAT('%',?1,'%')) ORDER BY b.title",
            nativeQuery = true)
    Page<Book> findAllByTitleOrAuthorByOrderByTitleAndByEnabled(String keyword, Pageable pageable);

    @Query( value = "SELECT * FROM Book b WHERE (b.title LIKE CONCAT('%',?1,'%') OR b.author LIKE CONCAT('%',?1,'%')) ORDER BY b.author",
            nativeQuery = true)
    Page<Book> findAllByTitleOrAuthorByOrderByAuthor(String keyword, Pageable pageable);

    @Query( value = "SELECT * FROM Book b WHERE (b.title LIKE CONCAT('%',?1,'%') OR b.author LIKE CONCAT('%',?1,'%')) ORDER BY b.created_at DESC",
            nativeQuery = true)
    Page<Book> findAllByTitleOrAuthorByOrderByCreatedAt(String keyword, Pageable pageable);
    List<Book> findByUser(Optional<User> user);
}
