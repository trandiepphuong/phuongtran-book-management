package com.phuongtd.book.controllers;

import com.phuongtd.book.entities.Book;
import com.phuongtd.book.services.BookService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
    Book addBookAsAdmin(@RequestBody Book book) throws ParseException {
        return bookService.addBookAsAdmin(book);
    }

    @PutMapping("/enable/{id}")
    Book enableBook(@PathVariable int id) throws NotFoundException {
        return bookService.enableBook(id);
    }

    @PutMapping("/unable/{id}")
    Book unableBook(@PathVariable int id) throws NotFoundException {
        return bookService.unableBook(id);
    }

    @PutMapping("/edit/{id}")
    Book editBookAsAdmin(@PathVariable int id, @RequestBody Book book) throws ParseException, NotFoundException {
        return bookService.update(id, book);
    }

    @DeleteMapping("/{id}")
    Book deleteBookAsAdmin(@PathVariable int id) throws NotFoundException {
        return bookService.deleteById(id);
    }
}
