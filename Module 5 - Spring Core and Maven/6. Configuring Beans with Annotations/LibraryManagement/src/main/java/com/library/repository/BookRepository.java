package com.library.repository;

import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class BookRepository {
    public List<String> findAllBooks() {
        return Arrays.asList("Clean Architecture", "Java Concurrency in Practice", "Spring Microservices");
    }
}
