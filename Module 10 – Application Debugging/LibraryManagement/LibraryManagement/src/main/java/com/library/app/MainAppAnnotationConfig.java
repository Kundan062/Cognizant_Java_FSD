package com.library.app;

import com.library.model.Book;
import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Exercise 6: Loads applicationContext-annotation.xml, which uses
 * <context:component-scan> instead of explicit <bean> tags. BookService and
 * BookRepositoryImpl are discovered automatically because they carry
 * @Service / @Repository annotations.
 */
public class MainAppAnnotationConfig {

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext-annotation.xml");

        BookService bookService = context.getBean("bookService", BookService.class);

        bookService.addBook(new Book("978-0132350884", "Clean Code", "Robert C. Martin"));

        System.out.println("Books in library (annotation-based config):");
        bookService.listBooks().forEach(System.out::println);
    }
}
