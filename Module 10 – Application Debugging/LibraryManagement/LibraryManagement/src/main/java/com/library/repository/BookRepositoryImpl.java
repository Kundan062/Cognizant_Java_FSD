package com.library.repository;

import com.library.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Exercise 6: annotated with @Repository so component-scanning can pick it up
 * automatically instead of declaring it as a <bean> in XML.
 * The @Repository annotation is a Spring stereotype annotation that also
 * marks this class as eligible for persistence-exception translation.
 */
@Repository("bookRepository")
public class BookRepositoryImpl implements BookRepository {

    private final List<Book> books = new ArrayList<>();

    @Override
    public void save(Book book) {
        books.add(book);
    }

    @Override
    public List<Book> findAll() {
        return Collections.unmodifiableList(books);
    }
}
