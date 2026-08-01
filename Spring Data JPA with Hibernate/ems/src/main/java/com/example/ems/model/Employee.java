package com.example.ems.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * Exercise 2: Employee entity with fields id, name, email, department.
 * Extends Auditable (Exercise 7) to pick up createdBy/createdDate/etc.
 *
 * Exercise 5: @NamedQueries / @NamedQuery example alongside repository
 * derived/@Query methods (see EmployeeRepository).
 *
 * Exercise 10: @DynamicInsert / @DynamicUpdate are Hibernate-specific
 * annotations - they make Hibernate skip null columns on INSERT and
 * unchanged columns on UPDATE, which reduces payload size and lets DB
 * column defaults kick in.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "employee")
@DynamicInsert
@DynamicUpdate
@NamedQueries({
        @NamedQuery(
                name = "Employee.findByDepartmentName",
                query = "SELECT e FROM Employee e WHERE e.department.name = :deptName"
        ),
        @NamedQuery(
                name = "Employee.countAll",
                query = "SELECT COUNT(e) FROM Employee e"
        )
})
public class Employee extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    public Employee(String name, String email, Department department) {
        this.name = name;
        this.email = email;
        this.department = department;
    }
}
