package com.cognizant.ems.projection;

public class EmployeeDepartmentDto {
    private final String employeeName;
    private final String departmentName;

    public EmployeeDepartmentDto(String employeeName, String departmentName) {
        this.employeeName = employeeName;
        this.departmentName = departmentName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getDepartmentName() {
        return departmentName;
    }
}
