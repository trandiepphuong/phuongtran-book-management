package com.phuongtd.book.services;

import com.phuongtd.book.entities.Book;
import com.phuongtd.book.repositories.BookRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = LocalDateTime.now().toString();
        String date = datetime.substring(0, 10);
        String time = datetime.substring(11, 19);
        return formatter.parse(date + " " + time);
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

    public List<Book> findByTitleOrAuthor(String keyword, String orderBy) {
        if (orderBy.equals("title")) {
            return filterEnabled(bookRepository.findByTitleOrAuthorByOrderByTitle(keyword));
        } else if (orderBy.equals("author")) {
            return filterEnabled(bookRepository.findByTitleOrAuthorByOrderByAuthor(keyword));
        } else if (orderBy.equals("created")) {
            return filterEnabled(bookRepository.findByTitleOrAuthorByOrderByCreatedAt(keyword));
        }
        return filterEnabled(bookRepository.findByTitleOrAuthor(keyword));
    }

    public Book findById(int id) throws NotFoundException {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        }
        throw new NotFoundException("Not found exception");
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
        throw new NotFoundException("Not found exception");
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
        throw new NotFoundException("Not found exception");
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
