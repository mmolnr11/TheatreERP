package com.project.controller;

import com.project.dao.EmployeeDao;
import com.project.dao.EventDao;
import com.project.model.Customer;
import com.project.model.Employee;
import com.project.model.Event;
import com.project.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class RestWebController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    EventDao eventDao;

//    @RequestMapping(value = "/getallcustomer", method = RequestMethod.GET)

    //    public Response getResource() {
    //        Response response = new Response("Done", cust);
    //        return response;
    //    }
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    long id = 1;

    @RequestMapping("/greeting")
    public List<Employee> employee(@RequestParam(value = "name", defaultValue = "World") String name) {
        List<Employee> myemp = employeeDao.getAllEmployee();

        return myemp;


    }

    @RequestMapping("/greeting2")
    public List<Event> employee2(@RequestParam(value = "name", defaultValue = "World") String name) {
        List<Event> myemp1 = eventDao.allEvent();
        for (int i = 0; i < myemp1.size() ; i++) {
            System.out.println(myemp1.get(i).getTitle());
        }

        return myemp1;


    }
}