package com.library.app;

import com.library.model.Book;
import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Exercises 1, 2 & 5: Loads the Spring context from applicationContext.xml,
 * proving that BookRepository is injected into BookService via SETTER injection
 * and the whole IoC container wiring works end to end.
 */
public class MainAppXmlConfig {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        BookService bookService = context.getBean("bookService", BookService.class);

        bookService.addBook(new Book("978-0134685991", "Effective Java", "Joshua Bloch"));
        bookService.addBook(new Book("978-0596007126", "Head First Design Patterns", "Freeman & Robson"));

        System.out.println("Books in library:");
        bookService.listBooks().forEach(System.out::println);
    }
}
