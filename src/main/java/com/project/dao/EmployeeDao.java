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
    public List<Employee> getAllEmployee(){return employeeRepository.findAll();}

    public void saveEmployee(Employee employee){
        employeeRepository.save(employee);
    }
    public Employee findEmployee(Long id){return employeeRepository.findOne(id);};
    //TODO renaming roles vs position
    public List<String> getEmployeeRoles (){
      return employeeRepository.getEmployeePositions();
    }
    public List<Employee> getEmmployessByRoles(String role){
        return employeeRepository.findAllByPosition(role);
    }

}
