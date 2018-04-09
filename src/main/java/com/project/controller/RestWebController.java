package com.project.controller;

import com.project.dao.EmployeeDao;
import com.project.dao.EventDao;
import com.project.dao.UserDao;
import com.project.model.*;
import com.project.service.DateValidation;
import com.project.service.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class RestWebController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    EventDao eventDao;

    @Autowired
    UserDao userDao;

    @Autowired
    UserValidation userValidation;

    @Autowired
    DateValidation dateValidation;

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

    @RequestMapping(value = "/event/search", method = RequestMethod.POST)
    public List<Event> searchAsAdmin(@RequestParam("searchdatestart") String startDate,
                                 @RequestParam("searchdateend") String endDate) throws ParseException {
        List<Date> dates = dateValidation.createDateNew(startDate,endDate);
        List<Event> eventList =  eventDao.findByDate(dates.get(0),dates.get(1));
        return eventList;


    }

    @RequestMapping(value = "/user/event/search", method = RequestMethod.POST)
    public List<Event> searchAsUser (@RequestParam("searchdatestart") String startDate,
                                 @RequestParam("searchdateend") String endDate) throws ParseException {
        List<Date> dates = dateValidation.createDateNew(startDate,endDate);
        List<Event> eventList =  eventDao.findByDate(dates.get(0),dates.get(1));
        return eventList;


    }

    @RequestMapping(value = "/event/add-user", method = RequestMethod.POST)
    public List<String> addUserToDb(@RequestParam Map<String,String> allRequestParam){
        String fname = allRequestParam.get("firstname");
        User user = new User(allRequestParam.get("firstname"),
                allRequestParam.get("lastname"),
                allRequestParam.get("email"),
                allRequestParam.get("password"),
                allRequestParam.get("role"),
                allRequestParam.get("position"));
        List<String> errorMessages = userValidation.validateRegistrationDatas(user,"majdPassword");
        if (errorMessages.size() == 0){
            userDao.saveUser(user);
        }
        return errorMessages;

    }

    @RequestMapping(value = "/event/change", method = RequestMethod.POST)
    public Event changeEvent(@RequestParam Map<String,String> allRequestParam){
        Long id = Long.valueOf(allRequestParam.get("id"));
        Event event = eventDao.findOne(id);
        event.setTitle(allRequestParam.get("title"));
        System.out.println("majomfidesz" + allRequestParam.get("startDateTime"));
        System.out.println(event.getStartDateTime());
        event.setDescription(allRequestParam.get("description"));
        event.setLocation(allRequestParam.get("location"));
        event.setType(allRequestParam.get("type"));
        eventDao.saveEvent(event);
        return event;

    }
}