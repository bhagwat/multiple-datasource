package com.example.multidc.repository.department;

import com.example.multidc.entity.department.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
