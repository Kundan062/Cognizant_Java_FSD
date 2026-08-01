package com.example.ems.controller;

import com.example.ems.model.Employee;
import com.example.ems.projection.EmployeeLabel;
import com.example.ems.projection.EmployeeNameOnly;
import com.example.ems.projection.EmployeeSummary;
import com.example.ems.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Exercise 4: Implement RESTful endpoints for Employee CRUD operations.
 * Exercise 5: /by-department uses the custom @Query / named query.
 * Exercise 6: /search combines pagination and sorting.
 * Exercise 8: /projections/* expose interface- and class-based projections.
 */
@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@Valid @RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    @GetMapping
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @Valid @RequestBody Employee employee) {
        return employeeService.update(id, employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        employeeService.delete(id);
    }

    // Exercise 6: Pagination + sorting search endpoint
    // e.g. GET /api/employees/search?name=an&page=0&size=10&sortBy=name&direction=asc
    @GetMapping("/search")
    public Page<Employee> search(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        return employeeService.search(name, page, size, sortBy, direction);
    }

    // Exercise 5: custom query / named query
    @GetMapping("/by-department")
    public List<Employee> byDepartment(@RequestParam String name) {
        return employeeService.findByDepartmentName(name);
    }

    // Exercise 8: Projections
    @GetMapping("/projections/summary")
    public List<EmployeeSummary> summary(@RequestParam Long departmentId) {
        return employeeService.summaryByDepartment(departmentId);
    }

    @GetMapping("/projections/names")
    public List<EmployeeNameOnly> names() {
        return employeeService.namesOnly();
    }

    @GetMapping("/projections/labels")
    public List<EmployeeLabel> labels() {
        return employeeService.labels();
    }
}
