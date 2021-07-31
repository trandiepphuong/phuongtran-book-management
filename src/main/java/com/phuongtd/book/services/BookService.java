package com.phuongtd.book.services;

import com.phuongtd.book.entities.Book;
import com.phuongtd.book.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
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
    public List<Book> filterEnabled(List<Book> bookList){
        List<Book> result = new ArrayList<>();
        for (Book book : bookList){
            if (book.isEnabled()) result.add(book);
        }
        return result;
    }
    public List<Book> findEnabledBook() {
        return filterEnabled(bookRepository.findAll());
    }

    public List<Book> findAllByOrderByTitle() {
        return filterEnabled(bookRepository.findAllByOrderByTitle());
    }

    public List<Book> findAllByOrderByAuthor() {
        return filterEnabled(bookRepository.findAllByOrderByAuthor());
    }

    public List<Book> findAllByOrderByCreatedAt() {
        return filterEnabled(bookRepository.findAllByOrderByCreatedAt());
    }

    public List<Book> findByTitleContainingOrAuthorContaining(String keyword) {
        return filterEnabled(bookRepository.findByTitleOrAuthor(keyword));
    }

    public ResponseEntity<?> findById(int id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return new ResponseEntity(book.get(), HttpStatus.OK);
        }
        return new ResponseEntity("Book ID is not available", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> addBook(Book book) throws ParseException {
        book.setCreatedAt(getCurrentTime());
        book.setUpdatedAt(getCurrentTime());
        book.setEnabled(false);
        return new ResponseEntity<>(bookRepository.save(book), HttpStatus.CREATED);
    }

    public ResponseEntity<?> deleteById(int id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.deleteById(id);
            return new ResponseEntity(book.get(), HttpStatus.OK);
        }
        return new ResponseEntity("Book ID is not available", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> update(int id, Book book) throws ParseException {
        Optional<Book> oldBook = bookRepository.findById(id);
        if (oldBook.isPresent()) {
            oldBook.get().setTitle(book.getTitle());
            oldBook.get().setUpdatedAt(getCurrentTime());
            oldBook.get().setAuthor(book.getAuthor());
            oldBook.get().setDescription(book.getDescription());
            oldBook.get().setCommentList(book.getCommentList());
            return new ResponseEntity<>(bookRepository.save(oldBook.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>("Book ID is not available", HttpStatus.NOT_FOUND);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public ResponseEntity<?> addBookAsAdmin(Book book) throws ParseException {
        book.setCreatedAt(getCurrentTime());
        book.setUpdatedAt(getCurrentTime());
        book.setEnabled(true);
        return new ResponseEntity<>(bookRepository.save(book), HttpStatus.CREATED);
    }

    public ResponseEntity<?> enableBook(int id) {
        Optional<Book> oldBook = bookRepository.findById(id);
        if (oldBook.isPresent()) {
            oldBook.get().setEnabled(true);
            return new ResponseEntity<>(bookRepository.save(oldBook.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>("Book ID is not available", HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<?> unableBook(int id) {
        Optional<Book> oldBook = bookRepository.findById(id);
        if (oldBook.isPresent()) {
            oldBook.get().setEnabled(false);
            return new ResponseEntity<>(bookRepository.save(oldBook.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>("Book ID is not available", HttpStatus.NOT_FOUND);
    }
}
