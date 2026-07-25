package com.library.service;

import com.library.model.Book;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Exercise 2 & 7: exposes BOTH a constructor and a setter for BookRepository
 * so it can be wired either way depending on which applicationContext file
 * is loaded (see applicationContext.xml vs applicationContext-constructor.xml).
 *
 * Exercise 6: annotated with @Service so it can be picked up by component
 * scanning instead of an explicit XML <bean> declaration.
 */
@Service("bookService")
public class BookService {

    private BookRepository bookRepository;

    // No-arg constructor kept so setter-injection XML config still works.
    public BookService() {
    }

    // Exercise 7: constructor injection.
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Exercise 2 & 7: setter injection.
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(Book book) {
        if (bookRepository == null) {
            throw new IllegalStateException("BookRepository has not been injected into BookService");
        }
        bookRepository.save(book);
    }

    public List<Book> listBooks() {
        return bookRepository.findAll();
    }
}
