package com.example.ems.projection;

/**
 * Exercise 8: Class-based projection (DTO), populated via a JPQL constructor
 * expression in EmployeeRepository (see findAllProjectedBy()).
 */
public class EmployeeNameOnly {

    private final Long id;
    private final String name;

    public EmployeeNameOnly(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
