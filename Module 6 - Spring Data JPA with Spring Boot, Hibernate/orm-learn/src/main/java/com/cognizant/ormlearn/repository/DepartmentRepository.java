package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    @Query("select distinct d from Department d left join fetch d.employeeList where d.id = :id")
    Department findDepartmentWithEmployees(@Param("id") Integer id);
}
