package com.example.ems.projection;

/**
 * Exercise 8: Interface-based projection.
 * Spring Data JPA proxies this interface at runtime and only selects the
 * columns needed to satisfy id/name/department name - avoids over-fetching.
 */
public interface EmployeeSummary {

    Long getId();

    String getName();

    // Nested/derived projection - dot notation reaches into the association
    String getDepartment_Name();

    // A "@Value" style computed projection is defined in EmployeeNameOnly below,
    // since SpEL @Value expressions need a concrete method, e.g.:
    // @Value("#{target.name + ' (' + target.department.name + ')'}")
    // String getDisplayLabel();
}
