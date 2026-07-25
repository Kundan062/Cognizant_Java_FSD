package com.library.app;

import com.library.model.Book;
import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Exercises 3 & 8: Uses applicationContext.xml (which enables
 * <aop:aspectj-autoproxy/> and registers LoggingAspect). Watch the console -
 * every call into BookService will print "before", "after", and timing logs
 * coming from the aspect, proving the cross-cutting logging concern is fully
 * decoupled from BookService's business logic.
 */
public class MainAppAopDemo {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        BookService bookService = context.getBean("bookService", BookService.class);

        bookService.addBook(new Book("978-0201633610", "Design Patterns", "Gang of Four"));
        bookService.listBooks();
    }
}
