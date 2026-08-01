package com.example.ems.config;

import com.example.ems.model.Department;
import com.example.ems.model.Employee;
import com.example.ems.repository.DepartmentRepository;
import com.example.ems.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Seeds sample Department/Employee data on startup (H2 in-memory resets each
 * run) so every endpoint in Exercises 4-8 can be exercised immediately.
 */
@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) {
        Department engineering = departmentRepository.save(new Department("Engineering"));
        Department sales = departmentRepository.save(new Department("Sales"));
        Department hr = departmentRepository.save(new Department("Human Resources"));

        employeeRepository.save(new Employee("Alice Johnson", "alice.johnson@example.com", engineering));
        employeeRepository.save(new Employee("Brian Chen", "brian.chen@example.com", engineering));
        employeeRepository.save(new Employee("Carla Diaz", "carla.diaz@example.com", sales));
        employeeRepository.save(new Employee("Dan O'Neil", "dan.oneil@example.com", sales));
        employeeRepository.save(new Employee("Elena Fischer", "elena.fischer@example.com", hr));
    }
}
