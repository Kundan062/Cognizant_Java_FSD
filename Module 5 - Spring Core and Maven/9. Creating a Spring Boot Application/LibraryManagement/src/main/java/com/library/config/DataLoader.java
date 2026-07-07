package com.library.config;

import com.library.entity.Book;
import com.library.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final BookRepository bookRepository;

    public DataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) {
        bookRepository.save(new Book("Clean Code", "Robert C. Martin", 599.00));
        bookRepository.save(new Book("Effective Java", "Joshua Bloch", 799.00));
    }
}
