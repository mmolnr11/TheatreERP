package com.project.dao;

import com.project.model.Employee;
import com.project.model.User;
import com.project.repository.EmployeeRepository;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;


@Service
public class EmployeeDao {
    @Autowired
    EmployeeRepository employeeRepository;

    public void saveEmployee(Employee employee){
        employeeRepository.save(employee);
    }
    public HashSet<Employee> listPosition(){
        return employeeRepository.findDistinctByPosition();
     };
}
