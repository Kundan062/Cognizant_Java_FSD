package com.cognizant.ems.controller;

import com.cognizant.ems.model.Employee;
import com.cognizant.ems.projection.EmployeeDepartmentDto;
import com.cognizant.ems.projection.EmployeeNameEmailProjection;
import com.cognizant.ems.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<Employee>(employeeService.create(employee), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.update(id, employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<Employee> searchEmployees(@RequestParam String name) {
        return employeeService.searchByName(name);
    }

    @GetMapping("/page")
    public Page<Employee> pageEmployees(@RequestParam(defaultValue = "") String name,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "5") int size,
                                        @RequestParam(defaultValue = "name") String sortBy,
                                        @RequestParam(defaultValue = "asc") String direction) {
        return employeeService.searchByName(name, page, size, sortBy, direction);
    }

    @GetMapping("/department/{departmentName}")
    public List<Employee> getEmployeesByDepartment(@PathVariable String departmentName) {
        return employeeService.findByDepartmentName(departmentName);
    }

    @GetMapping("/projection/{departmentName}")
    public List<EmployeeNameEmailProjection> getEmployeeProjection(@PathVariable String departmentName) {
        return employeeService.findProjectionByDepartment(departmentName);
    }

    @GetMapping("/projection/departments")
    public List<EmployeeDepartmentDto> getEmployeeDepartmentProjection() {
        return employeeService.findEmployeeDepartmentDtos();
    }

    @PostMapping("/batch")
    public List<Employee> createEmployeesInBatch(@RequestBody List<Employee> employees) {
        return employeeService.saveBatch(employees);
    }
}
