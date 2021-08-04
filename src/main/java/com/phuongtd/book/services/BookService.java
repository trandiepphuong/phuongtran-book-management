package com.phuongtd.book.services;

import com.phuongtd.book.entities.Book;
import com.phuongtd.book.repositories.BookRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public Date getCurrentTime() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = LocalDateTime.now().toString();
        String date = datetime.substring(0, 10);
        String time = datetime.substring(11, 19);
        return formatter.parse(date + " " + time);
    }

    public Page<Book> filterEnabled(Page<Book> bookList) {
        List<Book> temp = bookList.getContent();
        for (Book book : temp) {
            if (!book.isEnabled()) temp.remove(book);
        }
        Page<Book> pageTuts = new PageImpl<Book>(temp);
        return pageTuts;
    }

    public ResponseEntity<Map<String, Object>> findEnabledBook(int page, int size, String keyword, String orderBy) {
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Book> pageTuts;
            if (keyword == null) {
                pageTuts = bookRepository.findAllByEnabled(paging);
            } else {
                if (orderBy.equals("title")) {
                    pageTuts = bookRepository.findByTitleOrAuthorByOrderByTitleAndByEnabled(keyword, paging);
                    System.out.println("orderbytitle");
                } else if (orderBy.equals("author")) {
                    pageTuts = bookRepository.findByTitleOrAuthorByOrderByAuthor(keyword, paging);
                    System.out.println("orderbyauthor");
                } else if (orderBy.equals("created")) {
                    pageTuts = bookRepository.findByTitleOrAuthorByOrderByCreatedAt(keyword, paging);
                    System.out.println("orderbycreatedat");
                }
                else
                pageTuts = bookRepository.findByTitleOrAuthor(keyword, paging);
            }
            List<Book> books = pageTuts.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("books", books);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Book findById(int id) throws NotFoundException {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        }
        throw new NotFoundException("Book ID " + id + " is not found.");
    }

    public Book addBook(Book book) throws ParseException {
        book.setCreatedAt(getCurrentTime());
        book.setUpdatedAt(getCurrentTime());
        book.setEnabled(false);
        return bookRepository.save(book);
    }

    public Book deleteById(int id) throws NotFoundException {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.deleteById(id);
            return book.get();
        }
        throw new NotFoundException("Book ID " + id + " is not found.");
    }

    public Book update(int id, Book book) throws ParseException, NotFoundException {
        Optional<Book> oldBook = bookRepository.findById(id);
        if (oldBook.isPresent()) {
            oldBook.get().setTitle(book.getTitle());
            oldBook.get().setUpdatedAt(getCurrentTime());
            oldBook.get().setAuthor(book.getAuthor());
            oldBook.get().setDescription(book.getDescription());
            oldBook.get().setCommentList(book.getCommentList());
            return bookRepository.save(oldBook.get());
        }
        throw new NotFoundException("Book ID " + id + " is not found.");
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book addBookAsAdmin(Book book) throws ParseException {
        book.setCreatedAt(getCurrentTime());
        book.setUpdatedAt(getCurrentTime());
        book.setEnabled(true);
        return bookRepository.save(book);
    }

    public Book enableBook(int id) throws NotFoundException {
        Optional<Book> oldBook = bookRepository.findById(id);
        if (oldBook.isPresent()) {
            oldBook.get().setEnabled(true);
            return bookRepository.save(oldBook.get());
        }
        throw new NotFoundException("Not found exception");
    }

    public Book unableBook(int id) throws NotFoundException {
        Optional<Book> oldBook = bookRepository.findById(id);
        if (oldBook.isPresent()) {
            oldBook.get().setEnabled(false);
            return bookRepository.save(oldBook.get());
        }
        throw new NotFoundException("Not found exception");
    }
}
