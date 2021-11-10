package com.phuongtd.book.services;

import com.phuongtd.book.entities.Book;
import com.phuongtd.book.entities.Comment;
import com.phuongtd.book.repositories.BookRepository;
import com.phuongtd.book.repositories.CommentRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    BookRepository bookRepository;

    public Date getCurrentTime() throws ParseException {
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = LocalDateTime.now().toString();
        String date = datetime.substring(0, 10);
        String time = datetime.substring(11, 19);
        System.out.println(date + " " + time);
        return formatter1.parse(date + " " + time);
    }

    public List<Comment> getCommentsByBookId(int bookId) throws NotFoundException {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            List<Comment> comments = commentRepository.findByBook(book.get());
            return comments;
        }
        throw new NotFoundException("Not found exception");
    }

    public Comment addComment(int bookId, Comment comment) throws ParseException, NotFoundException {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            comment.setCreatedAt(getCurrentTime());
            comment.setUpdatedAt(getCurrentTime());
            comment.setBook(book.get());
            return commentRepository.save(comment);
        }
        throw new NotFoundException("Not found exception");
    }

    public Comment editComment(int commentId, Comment comment) throws ParseException, NotFoundException {
        Optional<Comment> oldComment = commentRepository.findById(commentId);
        if (oldComment.isPresent()) {
            oldComment.get().setUpdatedAt(getCurrentTime());
            oldComment.get().setMessage(comment.getMessage());
            return commentRepository.save(oldComment.get());
        }

        throw new NotFoundException("Not found exception");
    }

    public Comment delete(int commentId) throws NotFoundException {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()) {
            commentRepository.deleteById(commentId);
            return comment.get();
        }
        throw new NotFoundException("Not found exception");
    }

    public Comment findById(int id) throws NotFoundException {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()) {
            return comment.get();
        }
        throw new NotFoundException("Not found exception");
    }
}
