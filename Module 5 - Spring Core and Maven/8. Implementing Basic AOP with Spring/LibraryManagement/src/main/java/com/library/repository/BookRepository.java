package com.library.repository;

import java.util.Arrays;
import java.util.List;

public class BookRepository {
    public List<String> findAllBooks() {
        return Arrays.asList("AOP in Action", "Spring in Practice", "Pro Spring");
    }
}
