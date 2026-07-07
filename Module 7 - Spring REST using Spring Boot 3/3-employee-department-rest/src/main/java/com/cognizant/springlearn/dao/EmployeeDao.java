package com.cognizant.springlearn.dao;
import com.cognizant.springlearn.model.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public class EmployeeDao { @SuppressWarnings("unchecked") public List<Employee> getAllEmployees(){ ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml"); return context.getBean("employeeList", List.class); } }
