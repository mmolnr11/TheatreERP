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
    public String vlami (Model model, @RequestParam("startdate") String date) throws ParseException {
//        String formatted = date.substring(0,10);
        List<Date> dates = dateValidation.createDate(date);
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
}
