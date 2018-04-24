package com.project.controller;

import com.project.dao.*;
//import com.project.dao.RoleDao;
import com.project.model.*;
//import com.project.model.Role;
import com.project.service.DateValidation;
import com.project.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.*;

@Controller
public class EventController {
    @Autowired
    EventDao eventDao;
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DateValidation dateValidation;
    @Autowired
    UserDao userDao;
    @Autowired
    EventService eventService;
    @Autowired
    CommentDao commentDao;
    @Autowired
    DatesOfEventDao datesOfEventDao;


    @GetMapping(value = "/admin/event/{id}/description")
    public String renderEventDetailsPage(Principal principal, Model model, @PathVariable("id") Long id){
        Event event = eventDao.findOne(id);
        String roleCorrect = getPrincipalRole(principal);
        System.out.println("role " + roleCorrect);
        model.addAttribute("event", event);
        model.addAttribute("roleString", roleCorrect);
        return "material-update";
    }

//

    @GetMapping(value = "/user/event/{id}/description")
    public String userEventDetailsPage(Principal principal, Model model, @PathVariable("id") Long id){
        Event event = eventDao.findOne(id);
        List detailsOfOrderedEmployees = eventService.gettingDesiredEmpNumberToOneEvent(event,principal);
        String roleCorrect = (String) detailsOfOrderedEmployees.get(0);
        Integer wantedNumberOfEmployees = (Integer) detailsOfOrderedEmployees.get(1);
        List<Employee> notYetOrderedEmployees = eventService.selectingNotOrderedEmployees(event, roleCorrect);
//        int alreadyAssignedEmployees = event.getEmployeesToEvent().size();
//        int actualNumberOfEmployees = wantedNumberOfEmployees - alreadyAssignedEmployees;

        model.addAttribute("event", event);
        model.addAttribute("roleString", roleCorrect);
//        model.addAttribute("roleInteger", actualNumberOfEmployees);
        model.addAttribute("roleInteger", 32);
        model.addAttribute("employees", notYetOrderedEmployees);
        return "user-event-detail-material";
    }


    public String getPrincipalRole(Principal principal){
        User user = userDao.getUserByEmailAddress(principal.getName());
        return user.getPosition();

    }



    List<Employee> notYetOrderedEmployees = new ArrayList<Employee>();
    List<Employee> alreadyOrderedEmployees = new ArrayList<Employee>();

// TODO DELET THIS
    @GetMapping(value="event/user/{id}/description")
    public String userGetEventDescription(Model model, @PathVariable("id") Long id, Principal principal){
        Event event = eventDao.findOne(id);
        List detailsOfOrderedEmployees = eventService.gettingDesiredEmpNumberToOneEvent(event,principal);
        String roleCorrect = (String) detailsOfOrderedEmployees.get(0);
        Integer wantedNumberOfEmployees = (Integer) detailsOfOrderedEmployees.get(1);

        List<Employee> notYetOrderedEmployees = eventService.selectingNotOrderedEmployees(event, roleCorrect);
//        int alreadyAssignedEmployees = event.getEmployeesToEvent().size();
//        int actualNumberOfEmployees = wantedNumberOfEmployees - alreadyAssignedEmployees;

        model.addAttribute("event", event);
        model.addAttribute("roleString", roleCorrect);
        model.addAttribute("roleInteger", 45);
//        model.addAttribute("roleInteger", actualNumberOfEmployees);
        model.addAttribute("employees", notYetOrderedEmployees);
        return "user-event-detail";
    }



// TODO HTML for this rout



    @RequestMapping(value = "admin/event/{id}/datelist")
    public String addDateToEvent (Model model, @PathVariable("id") String id){
        Long eventId = Long.valueOf(id);
        Event event = eventDao.findOne(eventId);
        model.addAttribute("event", event);
        return "admin-datelist-to-event";
    }



    @RequestMapping(value = "admin/event/{eventid}/date/{dateid}")
    public String eventAtDate(Model model,Principal principal,
                              @PathVariable("eventid")String eventid,
                              @PathVariable("dateid")String dateid){
        Long eventId = Long.valueOf(eventid);
        Long dateId = Long.valueOf(dateid);
        Event event = eventDao.findOne(eventId);
        DatesOfEvent datesOfEvent = datesOfEventDao.findDate(dateId);
        String roleCorrect = getPrincipalRole(principal);
        model.addAttribute("event", event);
        model.addAttribute("roleCorrect", roleCorrect);
        model.addAttribute("datesOfEvent", datesOfEvent);
        return "admin-one-date-to-event-view";
    }

}
