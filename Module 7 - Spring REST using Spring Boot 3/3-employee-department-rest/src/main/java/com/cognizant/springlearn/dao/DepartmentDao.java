package com.cognizant.springlearn.dao;
import com.cognizant.springlearn.model.Department;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public class DepartmentDao { @SuppressWarnings("unchecked") public List<Department> getAllDepartments(){ ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml"); return context.getBean("departmentList", List.class); } }
