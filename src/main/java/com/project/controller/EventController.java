package com.project.controller;

import com.project.dao.EmployeeDao;
import com.project.dao.EventDao;
//import com.project.dao.RoleDao;
import com.project.model.Employee;
import com.project.model.Event;
//import com.project.model.Role;
import com.project.service.DateValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class EventController {
    @Autowired
    EventDao eventDao;
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DateValidation dateValidation;
//    @Autowired
//    RoleDao roleDao;

    @PostMapping(value = "/event-detail")
    public String vlami (Model model, @RequestParam("startdate") String startDate, @RequestParam("startdate") String endDate) throws ParseException {
//        String formatted = date.substring(0,10);
        List<Date> dates = dateValidation.createDate(startDate,endDate);
        List<Event> eventList =  eventDao.findByDate(dates.get(0),dates.get(1));
        System.out.println(eventList.size());
        model.addAttribute("eventList",eventList);
        return "superadmin";
    }

    @GetMapping(value = "/event/{id}/description")
    public String renderEventDetailsPage(Model model, @PathVariable("id") Long id){
        Event event = eventDao.findOne(id);
        model.addAttribute("event", event);
        return "update-event";
    }
    @PostMapping(value = "/event/{id}/update")
    public String saveChangesToLiveshow (@ModelAttribute("event") Event event,
                                         Model model, @PathVariable("id") Long id){
        Event eventOld = eventDao.findOne(id);
        System.out.println(event.getTitle());
        eventOld.setTitle(event.getTitle());
        eventOld.setDescription(event.getDescription());
        eventOld.setStartDateTime(event.getStartDateTime());
        eventOld.setEndDateTime(event.getEndDateTime());
        eventDao.saveEvent(eventOld);
        return "superadmin";
    }
    @GetMapping(value = "/event/{id}/delete")
    public String deleteEvent(@PathVariable("id") Long id){
        eventDao.deleteOne(id);
        return "superadmin";
    }
    @GetMapping(value = "/event/create")
    public String renderCreateEventTemplate(Model model){
        model.addAttribute("event", new Event());
//        HashSet<Employee> list = employeeDao.listPosition();
        List<String> roles = employeeDao.getEmployeeRoles();
//        System.out.println("anyuuu " + roles.toString());
        int numberOfRole = 0;
        model.addAttribute("roles",roles );
        model.addAttribute("numberOfRole",numberOfRole );
        return "create-event";
    }
    @PostMapping(value = "/event/create")
    public String saveNewEvent (@RequestParam HashMap<String,String> allRequestParams) throws ParseException {
        List<Date> dates = dateValidation.createDateFromForm(allRequestParams);
        Event newEvent = new Event(allRequestParams.get("description"),allRequestParams.get("title"),
                dates.get(0),dates.get(1),allRequestParams.get("location"),allRequestParams.get("type") );
        List<String> roles = employeeDao.getEmployeeRoles();
        HashMap<String,Integer > hmap = new HashMap<String, Integer>();


        for (int i = 0; i < roles.size(); i++) {
             hmap.put(roles.get(i),Integer.valueOf(allRequestParams.get(roles.get(i))));
        }
        newEvent.setEventhezDolgozok(hmap);
        eventDao.saveEvent(newEvent);
        return "superadmin";
    }

    @PostMapping(value = "/user/event-detail")
    public String renderUserEvents (Model model, @RequestParam("startdate") String startDate,@RequestParam("enddate") String endDate) throws ParseException {
        List<Date> dates = dateValidation.createDate(startDate, endDate);
        List<Event> eventList =  eventDao.findByDate(dates.get(0),dates.get(1));
        System.out.println(eventList.size());
        List<Employee> employees = employeeDao.getAllEmployee();
        model.addAttribute("eventList",eventList);
        model.addAttribute("employees",employees);
        return "user";}



    @GetMapping(value="event/user/{id}/description")
    public String vvv(Model model, @PathVariable("id") Long id){
        Event event = eventDao.findOne(id);
        List<Employee> employees = employeeDao.getAllEmployee();

        model.addAttribute("event", event);
        model.addAttribute("employees", employees);
        return "user-event-detail";}
}
