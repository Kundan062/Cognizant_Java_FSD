package com.cognizant.ormlearn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "department")
public class Department {
    @Id
    @Column(name = "dp_id")
    private Integer id;

    @Column(name = "dp_name")
    private String name;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private Set<Employee> employeeList = new HashSet<Employee>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(Set<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public String toString() {
        return "Department{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
