# LibraryManagement-Boot — Exercise 9 (Spring Boot)

Spring Boot app with Spring Web + Spring Data JPA + H2, exposing a full CRUD
REST API for `Book`.

## How to run

```bash
mvn spring-boot:run
```

The app starts on `http://localhost:8080`.

## Try the endpoints

```bash
# Create a book
curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{"isbn":"978-0134685991","title":"Effective Java","author":"Joshua Bloch"}'

# List all books
curl http://localhost:8080/api/books

# Get one book
curl http://localhost:8080/api/books/1

# Update a book
curl -X PUT http://localhost:8080/api/books/1 \
  -H "Content-Type: application/json" \
  -d '{"isbn":"978-0134685991","title":"Effective Java (3rd Ed)","author":"Joshua Bloch"}'

# Delete a book
curl -X DELETE http://localhost:8080/api/books/1
```

You can also browse the H2 console at `http://localhost:8080/h2-console`
(JDBC URL: `jdbc:h2:mem:librarydb`, user `sa`, no password) to see the data
directly.

## Exercise 9 checklist

| Step | Where it lives |
|---|---|
| Create Spring Boot project | this Maven project (`pom.xml` uses `spring-boot-starter-parent`) |
| Add Spring Web, Spring Data JPA, H2 | `pom.xml` dependencies |
| Configure `application.properties` | `src/main/resources/application.properties` |
| `Book` entity + `BookRepository` | `model/Book.java`, `repository/BookRepository.java` |
| REST controller for CRUD | `controller/BookController.java` |
| Run & test endpoints | `mvn spring-boot:run` + the `curl` commands above |

## Note on testing

Same as the other project — no Maven Central access in this sandbox, so this
wasn't compiled here. It follows the standard Spring Boot 2.7 / Spring Data
JPA conventions closely; run `mvn clean compile` locally to verify.
