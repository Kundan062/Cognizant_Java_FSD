package com.example.ems.repository;

import com.example.ems.model.Employee;
import com.example.ems.projection.EmployeeLabel;
import com.example.ems.projection.EmployeeNameOnly;
import com.example.ems.projection.EmployeeSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Exercise 3: Repository extending JpaRepository, with derived query methods.
 * Exercise 5: Query methods via @Query, plus a @NamedQuery invocation.
 * Exercise 6: Pagination and sorting via Page/Pageable.
 * Exercise 8: Interface- and class-based projections.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // ---- Exercise 3: Derived query methods ----
    List<Employee> findByDepartmentId(Long departmentId);

    List<Employee> findByNameContainingIgnoreCase(String namePart);

    Optional<Employee> findByEmail(String email);

    boolean existsByEmail(String email);

    // ---- Exercise 5: @Query (JPQL) ----
    @Query("SELECT e FROM Employee e WHERE e.department.name = :deptName")
    List<Employee> findEmployeesInDepartment(@Param("deptName") String deptName);

    // ---- Exercise 5: @Query (native SQL) ----
    @Query(value = "SELECT * FROM employee WHERE email LIKE %:domain%", nativeQuery = true)
    List<Employee> findByEmailDomainNative(@Param("domain") String domain);

    // ---- Exercise 5: Named query defined via @NamedQuery on the Employee entity ----
    List<Employee> findByDepartmentName(String deptName); // Spring Data resolves "Employee.findByDepartmentName" first

    // ---- Exercise 6: Pagination + sorting ----
    Page<Employee> findByNameContainingIgnoreCase(String namePart, Pageable pageable);

    Page<Employee> findAll(Pageable pageable);

    // ---- Exercise 8: Interface-based projections ----
    List<EmployeeSummary> findByDepartmentId(Long departmentId, org.springframework.data.domain.Sort sort);

    List<EmployeeLabel> findAllProjectedLabelsBy();

    // ---- Exercise 8: Class-based projection via constructor expression ----
    @Query("SELECT new com.example.ems.projection.EmployeeNameOnly(e.id, e.name) FROM Employee e")
    List<EmployeeNameOnly> findAllProjectedBy();

    // ---- Exercise 10: Bulk/batch-friendly modifying query ----
    @Modifying
    @Query("UPDATE Employee e SET e.department = null WHERE e.department.id = :deptId")
    int clearDepartmentAssignment(@Param("deptId") Long deptId);
}
