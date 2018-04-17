package com.project.controller;


import com.project.dao.EmployeeDao;
import com.project.model.Employee;
import com.project.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

@Controller
public class AdminController {


    @Autowired
    EmployeeDao employeeDao;

    @GetMapping(value = "/admin/reports")
    public String reports(Model model){
        List<Employee> employeeList = employeeDao.getAllEmployee();
        model.addAttribute("employeeList", employeeList);
        List<String> roles = employeeDao.getEmployeeRoles();
        model.addAttribute("roles", roles);

        return "admin-reports";

    }


// TODO finish employee page 
    @GetMapping(value="/admin/{id}/employeelpage")
    public String employeelpage(Model model, @PathVariable("id") Long id) {
        Employee employee = employeeDao.findEmployee(id);
        model.addAttribute("employee", employee);
        return "employee-page";
    }
}
