package com.phuongtd.book.controllers;

import com.phuongtd.book.entities.Book;
import com.phuongtd.book.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/admin/books")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BookAdminController {
    @Autowired
    BookService bookService;

    @GetMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @PostMapping
    ResponseEntity<?> addBookAsAdmin(@RequestBody Book book) throws ParseException {
        return bookService.addBookAsAdmin(book);
    }

    @PutMapping("/enable/{id}")
    ResponseEntity<?> enableBook(@PathVariable int id) throws ParseException {
        return bookService.enableBook(id);
    }

    @PutMapping("/unable/{id}")
    ResponseEntity<?> unableBook(@PathVariable int id) throws ParseException {
        return bookService.unableBook(id);
    }

    @PutMapping("/edit/{id}")
    ResponseEntity<?> editBookAsAdmin(@PathVariable int id, @RequestBody Book book) throws ParseException {
        return bookService.update(id, book);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteBookAsAdmin(@PathVariable int id){
        return bookService.deleteById(id);
    }
}
