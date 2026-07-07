package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("select distinct e from Employee e left join fetch e.department left join fetch e.skillList where e.permanent = true")
    List<Employee> getAllPermanentEmployees();

    @Query("select avg(e.salary) from Employee e where e.department.id = :id")
    double getAverageSalary(@Param("id") int id);

    @Query(value = "select * from employee", nativeQuery = true)
    List<Employee> getAllEmployeesNative();

    @Query("select distinct e from Employee e left join fetch e.department left join fetch e.skillList where e.id = :id")
    Employee findEmployeeWithDepartmentAndSkills(@Param("id") Integer id);
}
