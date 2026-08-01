package com.example.ems.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.HashSet;
import java.util.Set;

/**
 * Exercise 2: Department entity with fields id, name.
 * One-to-many relationship: a Department has many Employees.
 * Exercise 10: @DynamicUpdate is a Hibernate-specific annotation that makes
 * generated UPDATE statements include only the columns that actually changed.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "department")
@DynamicUpdate
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = false)
    private Set<Employee> employees = new HashSet<>();

    public Department(String name) {
        this.name = name;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        employee.setDepartment(this);
    }
}
