package com.example.ems.service;

import com.example.ems.exception.ResourceNotFoundException;
import com.example.ems.model.Department;
import com.example.ems.model.Employee;
import com.example.ems.projection.EmployeeLabel;
import com.example.ems.projection.EmployeeNameOnly;
import com.example.ems.projection.EmployeeSummary;
import com.example.ems.repository.DepartmentRepository;
import com.example.ems.repository.EmployeeRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Exercise 4: Implementing CRUD Operations for Employee.
 * Exercise 5: Query method usage (custom queries / named query).
 * Exercise 6: Pagination and Sorting.
 * Exercise 8: Projections.
 * Exercise 10: Batch processing with Hibernate for bulk operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final EntityManager entityManager;

    // ---- Exercise 4: CRUD ----
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Transactional(readOnly = true)
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }

    public Employee update(Long id, Employee updated) {
        Employee existing = findById(id);
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        if (updated.getDepartment() != null && updated.getDepartment().getId() != null) {
            Department department = departmentRepository.findById(updated.getDepartment().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Department not found with id: " + updated.getDepartment().getId()));
            existing.setDepartment(department);
        }
        return employeeRepository.save(existing);
    }

    public void delete(Long id) {
        Employee existing = findById(id);
        employeeRepository.delete(existing);
    }

    // ---- Exercise 6: Pagination and sorting combined in a search endpoint ----
    @Transactional(readOnly = true)
    public Page<Employee> search(String nameContains, int page, int size, String sortBy, String direction) {
        Sort.Direction dir = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(dir, sortBy));
        if (nameContains == null || nameContains.isBlank()) {
            return employeeRepository.findAll(pageable);
        }
        return employeeRepository.findByNameContainingIgnoreCase(nameContains, pageable);
    }

    // ---- Exercise 5: custom query / named query usage ----
    @Transactional(readOnly = true)
    public List<Employee> findByDepartmentName(String departmentName) {
        return employeeRepository.findByDepartmentName(departmentName);
    }

    // ---- Exercise 8: Projections ----
    @Transactional(readOnly = true)
    public List<EmployeeSummary> summaryByDepartment(Long departmentId) {
        return employeeRepository.findByDepartmentId(departmentId, Sort.by("name"));
    }

    @Transactional(readOnly = true)
    public List<EmployeeNameOnly> namesOnly() {
        return employeeRepository.findAllProjectedBy();
    }

    @Transactional(readOnly = true)
    public List<EmployeeLabel> labels() {
        return employeeRepository.findAllProjectedLabelsBy();
    }

    /**
     * Exercise 10: Batch processing with Hibernate for bulk operations.
     * Persists employees in JDBC batches (batch size configured via
     * spring.jpa.properties.hibernate.jdbc.batch_size). Periodically flushing
     * and clearing the persistence context keeps the batch from growing the
     * first-level cache unbounded during a large bulk insert.
     */
    public void batchInsert(List<Employee> employees) {
        final int batchSize = 30;
        for (int i = 0; i < employees.size(); i++) {
            entityManager.persist(employees.get(i));
            if (i % batchSize == 0 && i > 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        entityManager.flush();
        entityManager.clear();
    }
}
