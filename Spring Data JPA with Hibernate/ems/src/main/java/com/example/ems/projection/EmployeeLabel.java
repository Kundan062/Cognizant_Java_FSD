package com.example.ems.projection;

import org.springframework.beans.factory.annotation.Value;

/**
 * Exercise 8: Interface-based projection using @Value + SpEL to control the
 * fetched/derived data (a computed "label" that isn't a plain entity field).
 */
public interface EmployeeLabel {

    @Value("#{target.name + ' - ' + (target.department != null ? target.department.name : 'Unassigned')}")
    String getLabel();
}
