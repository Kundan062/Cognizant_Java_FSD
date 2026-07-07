package com.library.repository;

import java.util.Arrays;
import java.util.List;

public class BookRepository {
    public List<String> findAllBooks() {
        return Arrays.asList("Patterns of Enterprise Application Architecture", "Test Driven Development", "Spring Recipes");
    }
}
