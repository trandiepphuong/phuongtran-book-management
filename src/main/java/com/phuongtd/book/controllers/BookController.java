package com.phuongtd.book.controllers;

import com.phuongtd.book.entities.Book;
import com.phuongtd.book.services.BookService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping("/search")
    public List<Book> findByTitleOrAuthor(@RequestParam String keyword, @RequestParam String orderBy) {
        return bookService.findByTitleOrAuthor(keyword,orderBy);
    }

    @GetMapping("/{id}")
    public Book findById(@PathVariable int id) throws NotFoundException {
        return bookService.findById(id);
    }

    @PostMapping()
    public Book addBook(@RequestBody Book book) throws ParseException {
        return bookService.addBook(book);
    }

    @DeleteMapping("/{id}")
    public Book deleteBook(@PathVariable int id) throws NotFoundException {
        return bookService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable int id, @RequestBody Book book) throws ParseException, NotFoundException {
        return bookService.update(id, book);
    }
}
