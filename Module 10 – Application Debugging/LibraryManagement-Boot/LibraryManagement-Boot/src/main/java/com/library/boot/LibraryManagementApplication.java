package com.library.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Exercise 9: Entry point for the Spring Boot version of the library
 * management system. Spring Boot auto-configures the embedded web server,
 * DataSource (H2), and JPA/Hibernate based on the dependencies on the
 * classpath and application.properties below.
 */
@SpringBootApplication
public class LibraryManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementApplication.class, args);
    }
}
