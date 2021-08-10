package com.phuongtd.book.controllers;

import com.phuongtd.book.entities.Comment;
import com.phuongtd.book.services.CommentService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/{bookId}")
    public List<Comment> getCommentsByBookId(@PathVariable int bookId) throws NotFoundException {
        return commentService.getCommentsByBookId(bookId);
    }

    @PostMapping("/{bookId}")
    public Comment addComment(@PathVariable int bookId, @RequestBody Comment comment) throws ParseException, NotFoundException {
        return commentService.addComment(bookId, comment);
    }

    @PutMapping("/{commentId}")
    public Comment editComment(@PathVariable int commentId, @RequestBody Comment comment) throws ParseException, NotFoundException {
        return commentService.editComment(commentId, comment);
    }

    @DeleteMapping("/{commentId}")
    public Comment deleteComment(@PathVariable int commentId) throws NotFoundException {
        return commentService.delete(commentId);
    }
}
