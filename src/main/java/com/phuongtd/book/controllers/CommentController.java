package com.phuongtd.book.controllers;

import com.phuongtd.book.entities.Comment;
import com.phuongtd.book.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/{bookId}")
    public ResponseEntity<?> getCommentsByBookId(@PathVariable int bookId) {
        return commentService.getCommentsByBookId(bookId);
    }

    @PostMapping("/{bookId}")
    public ResponseEntity<?> addComment(@PathVariable int bookId, @RequestBody Comment comment) throws ParseException {
        return commentService.addComment(bookId, comment);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<?> editComment(@PathVariable int commentId, @RequestBody Comment comment) throws ParseException {
        return commentService.editComment(commentId, comment);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable int commentId) {
        return commentService.delete(commentId);
    }
}
