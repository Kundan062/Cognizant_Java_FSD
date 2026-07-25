package com.library.repository;

import com.library.model.Book;

import java.util.List;

/**
 * Repository contract for Book persistence operations.
 * In these exercises it is backed by an in-memory list (no real database),
 * since the focus is on Spring wiring, not data access.
 */
public interface BookRepository {

    void save(Book book);

    List<Book> findAll();
}
