package com.cognizant.ems.service;

import com.cognizant.ems.model.Department;
import com.cognizant.ems.model.Employee;
import com.cognizant.ems.projection.EmployeeDepartmentDto;
import com.cognizant.ems.projection.EmployeeNameEmailProjection;
import com.cognizant.ems.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentService departmentService) {
        this.employeeRepository = employeeRepository;
        this.departmentService = departmentService;
    }

    @Transactional(readOnly = true)
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Employee not found: " + id));
    }

    @Transactional
    public Employee create(Employee employee) {
        attachDepartment(employee);
        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee update(Long id, Employee employee) {
        Employee existing = findById(id);
        existing.setName(employee.getName());
        existing.setEmail(employee.getEmail());
        existing.setDepartment(employee.getDepartment());
        attachDepartment(existing);
        return employeeRepository.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        employeeRepository.delete(findById(id));
    }

    @Transactional(readOnly = true)
    public List<Employee> searchByName(String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }

    @Transactional(readOnly = true)
    public Page<Employee> searchByName(String name, int page, int size, String sortBy, String direction) {
        Sort sort = "desc".equalsIgnoreCase(direction) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return employeeRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    @Transactional(readOnly = true)
    public List<Employee> findByDepartmentName(String departmentName) {
        return employeeRepository.findEmployeesByDepartmentName(departmentName);
    }

    @Transactional(readOnly = true)
    public List<EmployeeNameEmailProjection> findProjectionByDepartment(String departmentName) {
        return employeeRepository.findProjectedByDepartmentName(departmentName);
    }

    @Transactional(readOnly = true)
    public List<EmployeeDepartmentDto> findEmployeeDepartmentDtos() {
        return employeeRepository.fetchEmployeeDepartmentDtos();
    }

    @Transactional
    public List<Employee> saveBatch(List<Employee> employees) {
        employees.forEach(this::attachDepartment);
        return employeeRepository.saveAll(employees);
    }

    private void attachDepartment(Employee employee) {
        if (employee.getDepartment() != null && employee.getDepartment().getId() != null) {
            Department department = departmentService.findById(employee.getDepartment().getId());
            employee.setDepartment(department);
        }
    }
}
