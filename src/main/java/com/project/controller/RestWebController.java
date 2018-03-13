package com.project.controller;

import com.project.dao.EmployeeDao;
import com.project.model.Customer;
import com.project.model.Employee;
import com.project.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class RestWebController {

    @Autowired
    EmployeeDao employeeDao;

//    @RequestMapping(value = "/getallcustomer", method = RequestMethod.GET)

    //    public Response getResource() {
    //        Response response = new Response("Done", cust);
    //        return response;
    //    }
}