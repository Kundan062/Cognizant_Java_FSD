# LibraryManagement — Exercises 1–8 (Classic Spring, Maven)

One Maven project, several `applicationContext*.xml` files and `MainApp*`
runner classes so each exercise can be run independently while reusing the
same `Book` / `BookRepository` / `BookService` / `LoggingAspect` classes.

## How to build & run

```bash
mvn clean compile

# Exercise 1, 2 & 5 — basic Spring setup + IoC container + setter injection
mvn exec:java -Dexec.mainClass="com.library.app.MainAppXmlConfig"

# Exercise 3 & 8 — AOP logging (before/after/around advice, execution timing)
mvn exec:java -Dexec.mainClass="com.library.app.MainAppAopDemo"

# Exercise 6 — annotation-based config (@Service, @Repository, component-scan)
mvn exec:java -Dexec.mainClass="com.library.app.MainAppAnnotationConfig"

# Exercise 7 — constructor injection variant
mvn exec:java -Dexec.mainClass="com.library.app.MainAppConstructorInjection"
```

(If you don't have the `exec-maven-plugin` configured, just run the class
directly from your IDE — each `MainApp*` class has a `main` method.)

## Exercise → File map

| Exercise | What it asks for | Where it lives |
|---|---|---|
| 1. Configuring a Basic Spring Application | Maven project, `pom.xml`, `applicationContext.xml`, `BookService`, `BookRepository` | `pom.xml`, `applicationContext.xml`, `service/BookService.java`, `repository/BookRepositoryImpl.java` |
| 2. Implementing Dependency Injection | Wire `BookRepository` into `BookService`, setter method | `applicationContext.xml` (`<property>` tag), `BookService.setBookRepository()` |
| 3. Implementing Logging with Spring AOP | AOP dependency, aspect logging execution time | `pom.xml` (spring-aop, aspectjweaver), `aspect/LoggingAspect.java` (`@Around`), `applicationContext.xml` (`<aop:aspectj-autoproxy/>`) |
| 4. Creating and Configuring a Maven Project | Spring Context/AOP/WebMVC deps, compiler plugin for Java 1.8 | `pom.xml` |
| 5. Configuring the Spring IoC Container | Central `applicationContext.xml` for all beans | `applicationContext.xml` |
| 6. Configuring Beans with Annotations | `@Service`, `@Repository`, component scanning | `applicationContext-annotation.xml`, annotations on `BookService` / `BookRepositoryImpl` |
| 7. Constructor and Setter Injection | Both injection styles | `applicationContext-constructor.xml` (constructor), `applicationContext.xml` (setter) |
| 8. Basic AOP with Spring | Separate aspect, before/after advice, auto-proxying | `aspect/LoggingAspect.java` (`@Before`/`@After`), `applicationContext.xml` |

## Note on testing

This sandbox has no access to Maven Central, so the project could not be
compiled here. The code was written and reviewed carefully by hand against
Spring 5.3 / AspectJ 1.9 APIs — run `mvn clean compile` on your machine (with
internet access) to build it, and let me know if you hit any errors.
