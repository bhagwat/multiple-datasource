package com.example.multidc;

import java.util.ArrayList;

import com.example.multidc.entity.department.Department;
import com.example.multidc.entity.employee.Address;
import com.example.multidc.entity.employee.Employee;
import com.example.multidc.repository.department.DepartmentRepository;
import com.example.multidc.repository.employee.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MultidcApplication {
    final
    EmployeeRepository employeeRepository;
    final
    DepartmentRepository departmentRepository;

    public MultidcApplication(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @PostConstruct
    public void init() {
        var employees = new ArrayList<Employee>();
        var employee1 = new Employee("Bhagwat", "Delivery", 100.10d, "bhagwat.kumar@gmail.com", 40);
        employee1.addAddress(new Address("Delhi"));
        employee1.addAddress(new Address("Noida"));
        employees.add(employee1);

        var employee2 = new Employee("Ajam", "HR", 200.30, "ajam.kumar@gmail.com", 30);
        employee2.addAddress(new Address("Faridabad"));
        employee2.addAddress(new Address("Delhi"));
        employee2.addAddress(new Address("Patna"));
        employees.add(employee2);

        employeeRepository.saveAll(employees);

        var departments = new ArrayList<Department>();
        departments.add(new Department(2001L, "HR", "xxx"));
        departments.add(new Department(2002L, "Delivery", "yyy"));
        departmentRepository.saveAll(departments);

//        employeeRepository.findAll().forEach(System.out::println);
        employeeRepository.findBySalaryGreaterThan(200.0d).forEach(System.out::println);
        employeeRepository.findBySalaryGreaterThanUsingJpql(200.0d).forEach(System.out::println);
        employeeRepository.findBySalaryGreaterThanUsingSql(200.0d).forEach(System.out::println);
        System.out.println("Average Salary: " + employeeRepository.avgSalary());
    }

    public static void main(String[] args) {
        SpringApplication.run(MultidcApplication.class, args);
    }
}
