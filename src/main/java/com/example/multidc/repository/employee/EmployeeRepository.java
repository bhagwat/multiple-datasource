package com.example.multidc.repository.employee;

import java.util.List;

import com.example.multidc.entity.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findBySalaryGreaterThan(double salary);

    @Query(value = "select e from Employee e where e.salary  > :salary")
    List<Employee> findBySalaryGreaterThanUsingJpql(@Param("salary") double salary);

    @Query(value = "select * from employee where salary  > ?1", nativeQuery = true)
    List<Employee> findBySalaryGreaterThanUsingSql(double salary);

    @Query(value = "select avg(e.salary) from Employee e")
    double avgSalary();
}
