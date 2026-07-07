package com.cognizant.ems.repository;

import com.cognizant.ems.model.Employee;
import com.cognizant.ems.projection.EmployeeDepartmentDto;
import com.cognizant.ems.projection.EmployeeNameEmailProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByNameContainingIgnoreCase(String name);

    Optional<Employee> findByEmail(String email);

    Optional<Employee> findByEmailNamed(String email);

    List<Employee> findByDepartmentNameNamed(String departmentName);

    Page<Employee> findByNameContainingIgnoreCase(String name, Pageable pageable);

    List<EmployeeNameEmailProjection> findProjectedByDepartmentName(String departmentName);

    @Query("select e from Employee e where lower(e.department.name) = lower(?1)")
    List<Employee> findEmployeesByDepartmentName(String departmentName);

    @Query("select new com.cognizant.ems.projection.EmployeeDepartmentDto(e.name, e.department.name) from Employee e")
    List<EmployeeDepartmentDto> fetchEmployeeDepartmentDtos();
}
