package com.project.repository;

import com.project.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Override
    Employee findOne(Long aLong);
//    @Query("SELECT e.position FROM Employee e ")
//    HashSet<Employee> findDistinctByPosition();
    List<String> getEmployeePositions();

    @Override
    List<Employee> findAll();

    List<Employee> findAllByPosition(String role);

//    Employee findByName(String name);

    //    List<Employee> getAllEmployee();
}

