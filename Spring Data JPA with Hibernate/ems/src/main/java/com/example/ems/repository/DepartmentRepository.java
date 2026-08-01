package com.example.ems.repository;

import com.example.ems.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Exercise 3: Repository extending JpaRepository, with derived query methods.
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findByName(String name);

    List<Department> findByNameStartingWithIgnoreCase(String prefix);

    boolean existsByName(String name);

    // Exercise 5: @Query with aggregate function - employee headcount per department
    @Query("SELECT d.name, COUNT(e) FROM Department d LEFT JOIN d.employees e "
            + "WHERE d.id = :departmentId GROUP BY d.name")
    List<Object[]> countEmployeesInDepartment(@Param("departmentId") Long departmentId);
}
