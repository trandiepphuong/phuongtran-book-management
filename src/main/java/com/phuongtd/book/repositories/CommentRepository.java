package com.phuongtd.book.repositories;

import com.phuongtd.book.entities.Book;
import com.phuongtd.book.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
    List<Comment> findByBook(Book book);
}
