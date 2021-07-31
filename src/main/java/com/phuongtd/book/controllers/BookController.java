package com.phuongtd.book.controllers;

import com.phuongtd.book.entities.Book;
import com.phuongtd.book.services.BookService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping
    public List<Book> getEnabledBook() {
        return bookService.findEnabledBook();
    }

    @GetMapping("/sort")
    public List<Book> findAllOrderByTitle(@RequestParam String orderBy) {
        if (orderBy.equals("title"))
            return bookService.findAllByOrderByTitle();
        else if (orderBy.equals("author")) return bookService.findAllByOrderByAuthor();
        return bookService.findAllByOrderByCreatedAt();
    }

    @GetMapping("/search")
    public List<Book> findByTitleContainingOrAuthorContaining(@RequestParam String keyword) {
        return bookService.findByTitleContainingOrAuthorContaining(keyword);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        return bookService.findById(id);
    }

    @PostMapping()
    public ResponseEntity<?> addBook(@RequestBody Book book) throws ParseException {
        return bookService.addBook(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable int id) throws ParseException {
        return bookService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Book book) throws ParseException {
        return bookService.update(id, book);
    }
}
