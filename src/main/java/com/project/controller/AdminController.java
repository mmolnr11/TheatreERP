package com.project.controller;


import com.project.dao.EmployeeDao;
import com.project.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {


    @Autowired
    EmployeeDao employeeDao;

    @GetMapping(value = "/admin/reports")
    public String reports(Model model){
        List<Employee> employeeList = employeeDao.getAllEmployee();
        for (Employee employee: employeeList) {
            System.out.println(employee.getPosition()+ " emp name");

        }
        model.addAttribute("employeeList", employeeList);
        List<String> roles = employeeDao.getEmployeeRoles();
        model.addAttribute("roles", roles);

        return "admin-reports";

    }

}
