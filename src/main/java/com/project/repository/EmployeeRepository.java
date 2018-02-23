package com.project.repository;

import com.project.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Override
    Employee findOne(Long aLong);
}
