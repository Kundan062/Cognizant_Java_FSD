package com.library.boot.repository;

import com.library.boot.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Exercise 9: Spring Data JPA repository. Extending JpaRepository gives us
 * save(), findAll(), findById(), deleteById(), etc. for free - no
 * implementation class needed, Spring generates one at runtime.
 */
public interface BookRepository extends JpaRepository<Book, Long> {
}
