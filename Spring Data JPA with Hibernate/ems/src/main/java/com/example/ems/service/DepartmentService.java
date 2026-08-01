package com.example.ems.service;

import com.example.ems.exception.ResourceNotFoundException;
import com.example.ems.model.Department;
import com.example.ems.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Exercise 4: Implementing CRUD Operations for Department.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public Department create(Department department) {
        return departmentRepository.save(department);
    }

    @Transactional(readOnly = true)
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Department findById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
    }

    public Department update(Long id, Department updated) {
        Department existing = findById(id);
        existing.setName(updated.getName());
        return departmentRepository.save(existing);
    }

    public void delete(Long id) {
        Department existing = findById(id);
        departmentRepository.delete(existing);
    }
}
