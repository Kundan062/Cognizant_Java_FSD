package com.cognizant.ems.service;

import com.cognizant.ems.model.Department;
import com.cognizant.ems.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Transactional(readOnly = true)
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Department findById(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Department not found: " + id));
    }

    @Transactional
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Transactional
    public Department update(Long id, Department department) {
        Department existing = findById(id);
        existing.setName(department.getName());
        return departmentRepository.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        departmentRepository.delete(findById(id));
    }
}
