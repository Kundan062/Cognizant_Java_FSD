package com.library.app;

import com.library.model.Book;
import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Exercise 7: Loads applicationContext-constructor.xml, which wires
 * BookRepository into BookService via CONSTRUCTOR injection instead of a setter.
 */
public class MainAppConstructorInjection {

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext-constructor.xml");

        BookService bookService = context.getBean("bookService", BookService.class);

        bookService.addBook(new Book("978-1491950357", "Building Microservices", "Sam Newman"));

        System.out.println("Books in library (constructor injection):");
        bookService.listBooks().forEach(System.out::println);
    }
}
