package com.phuongtd.book.controllers;

import com.phuongtd.book.entities.Book;
import com.phuongtd.book.services.BookService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/books")
public class AdminBookController {
    @Autowired
    BookService bookService;

    @GetMapping
    public Map<String, Object> findAll(@RequestParam(required = false) String keyword,
                                              @RequestParam(required = false, defaultValue = "") String orderBy,
                                              @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "3") int size) throws NotFoundException {
        return bookService.findAll(page, size, keyword, orderBy);
    }

    @PostMapping
    Book addBookAsAdmin(@RequestBody Book book) throws ParseException {
        return bookService.addBookAsAdmin(book);
    }

    @PutMapping("/{id}/enable")
    Book enableBook(@PathVariable int id) throws NotFoundException {
        return bookService.enableBook(id);
    }

    @PutMapping("/{id}/disable")
    Book disableBook (@PathVariable int id) throws NotFoundException {
        return bookService.disableBook (id);
    }

    @PutMapping("/{id}")
    Book editBookAsAdmin(@PathVariable int id, @RequestBody Book book) throws ParseException, NotFoundException {
        return bookService.update(id, book);
    }

    @DeleteMapping("/{id}")
    Book deleteBookAsAdmin(@PathVariable int id) throws NotFoundException {
        return bookService.deleteById(id);
    }
}
