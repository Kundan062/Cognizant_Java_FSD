package com.example.ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Exercise 1: Creating a Spring Boot Project.
 * Exercise 7: @EnableJpaAuditing turns on entity auditing support so that
 * @CreatedDate / @LastModifiedDate / @CreatedBy / @LastModifiedBy are populated
 * automatically. auditorAwareRef points at the AuditorAwareImpl bean (Exercise 7).
 */
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EntityScan(basePackages = "com.example.ems.model")
public class EmployeeManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementSystemApplication.class, args);
    }
}
