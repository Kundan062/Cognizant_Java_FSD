# Employee Management System — Spring Data JPA & Hibernate Hands-on

A complete Spring Boot project implementing **all 10 exercises** from
*Spring Data JPA and Hibernate.docx*. Everything runs on an in-memory H2
database, so there's nothing to install beyond a JDK — no MySQL/Eclipse
setup required.

## Quick start

```bash
mvn spring-boot:run
```

The app starts on `http://localhost:8080`, seeds sample data (see
`DataSeeder.java`), and the H2 console is available at
`http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:testdb`, user
`sa`, password `password`).

## Exercise → file map

| Exercise | What it asks for | Where it's implemented |
|---|---|---|
| **1. Setup** | Spring Boot project + Spring Data JPA, H2, Web, Lombok; H2 connection properties | `pom.xml`, `src/main/resources/application.properties` |
| **2. Entities** | `Employee` (id, name, email, department) and `Department` (id, name); one-to-many mapping | `model/Employee.java`, `model/Department.java` |
| **3. Repositories** | `EmployeeRepository` / `DepartmentRepository` extending `JpaRepository`, derived query methods | `repository/EmployeeRepository.java`, `repository/DepartmentRepository.java` |
| **4. CRUD** | Full CRUD + REST endpoints | `service/EmployeeService.java`, `service/DepartmentService.java`, `controller/EmployeeController.java`, `controller/DepartmentController.java` |
| **5. Query methods** | Keyword-derived queries, `@Query`, `@NamedQuery`/`@NamedQueries` | `EmployeeRepository` (`findEmployeesInDepartment`, `findByEmailDomainNative`, `findByDepartmentName`), `Employee.java` (`@NamedQueries`) |
| **6. Pagination & sorting** | `Page`/`Pageable`, combined search endpoint | `EmployeeRepository.findByNameContainingIgnoreCase(..., Pageable)`, `EmployeeService.search()`, `GET /api/employees/search` |
| **7. Auditing** | `@CreatedBy`, `@LastModifiedBy`, `@CreatedDate`, `@LastModifiedDate` | `model/Auditable.java`, `config/AuditorAwareImpl.java`, `@EnableJpaAuditing` in the main class |
| **8. Projections** | Interface-based, `@Value`/SpEL, and class-based (constructor expression) projections | `projection/EmployeeSummary.java`, `projection/EmployeeLabel.java`, `projection/EmployeeNameOnly.java`, exposed via `EmployeeRepository` + `/api/employees/projections/*` |
| **9. Data source config** | Auto-configuration + externalized properties; pattern for a second data source | `application.properties` (`app.datasource.secondary.*`), `config/DataSourceConfig.java` |
| **10. Hibernate-specific features** | Hibernate annotations, dialect/perf properties, batch processing | `@DynamicInsert`/`@DynamicUpdate` on `Employee`/`Department`, `hibernate.jdbc.batch_size` etc. in `application.properties`, `EmployeeService.batchInsert()` |

## REST API summary

| Method | Path | Purpose |
|---|---|---|
| POST | `/api/departments` | Create department |
| GET | `/api/departments` | List departments |
| GET | `/api/departments/{id}` | Get department |
| PUT | `/api/departments/{id}` | Update department |
| DELETE | `/api/departments/{id}` | Delete department |
| POST | `/api/employees` | Create employee |
| GET | `/api/employees` | List employees |
| GET | `/api/employees/{id}` | Get employee |
| PUT | `/api/employees/{id}` | Update employee |
| DELETE | `/api/employees/{id}` | Delete employee |
| GET | `/api/employees/search?name=&page=&size=&sortBy=&direction=` | Paginated + sorted search (Ex. 6) |
| GET | `/api/employees/by-department?name=` | Query-method lookup (Ex. 5) |
| GET | `/api/employees/projections/summary?departmentId=` | Interface projection (Ex. 8) |
| GET | `/api/employees/projections/names` | Class-based projection (Ex. 8) |
| GET | `/api/employees/projections/labels` | SpEL `@Value` projection (Ex. 8) |

## Notes

- `spring.jpa.hibernate.ddl-auto=update` lets Hibernate create the schema
  from the entities automatically against the in-memory H2 database — no
  manual DDL scripts needed for this demo.
- Auditing falls back to a fixed `"system"` auditor since no Spring
  Security login is wired up in this demo; swap in real authentication and
  `AuditorAwareImpl` will pick up the logged-in username automatically.
- This project wasn't build-verified with Maven in the environment that
  generated it (no Maven Central access there), so run `mvn spring-boot:run`
  or `mvn clean verify` locally as your first step and let me know if
  anything needs fixing.
