package com.project.controller;

import com.project.model.Response;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class RestWebController {

    List<String> cust = new ArrayList<String>();

//    @RequestMapping(value = "/getallcustomer", method = RequestMethod.GET)
//    public Response getResource() {
//        Response response = new Response("Done", cust);
//        return response;
//    }

    @RequestMapping(value = "/postcustomer", method = RequestMethod.POST)
    public Response postCustomer(@RequestParam HashMap<String,String> allRequestParams) {
//        cust.add(customer);
        // Create Response Object
        Response response = new Response("Done", "torold ki");
        return response;
    }
}