package com.project.controller;

import com.project.dao.CommentDao;
import com.project.dao.EmployeeDao;
import com.project.dao.EventDao;
//import com.project.dao.RoleDao;
import com.project.dao.UserDao;
import com.project.model.*;
//import com.project.model.Role;
import com.project.service.DateValidation;
import netscape.security.UserTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    @Autowired
    UserDao userDao;
    @Autowired
    CommentDao commentDao;
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
//        List<Employee> employees = employeeDao.getAllEmployee();
        model.addAttribute("eventList",eventList);
//        model.addAttribute("employees",employees);
        return "user";}




    List<Employee> cust = new ArrayList<Employee>();


    @GetMapping(value="event/user/{id}/description")
    public String vvv(Model model, @PathVariable("id") Long id, Principal principal){
        Event event = eventDao.findOne(id);
        Map<String, Integer> eployeesMap =event.getEventhezDolgozok();
        String role = getPrincipalRole(principal);
        String roleCorrect = "";
        Integer roleInteger = 0;
        for (Map.Entry<String,Integer> entry : eployeesMap.entrySet())
            if(entry.getKey().equals(role)){
                roleCorrect = entry.getKey();
                roleInteger = entry.getValue();
            }
//        List<Employee> employees = employeeDao.getAllEmployee();

        List<Employee> employees = employeeDao.getEmmployessByRoles(role);
        System.out.println("lista " + event.getComments().size());
        cust = employees;

        model.addAttribute("event", event);
        model.addAttribute("roleString", roleCorrect);
        model.addAttribute("roleInteger", roleInteger);
        model.addAttribute("employees", cust);
        return "user-event-detail";
    }



    @RequestMapping(value = "/postcustomer", method = RequestMethod.POST)
    @ResponseBody
    public Response postEmployee(@RequestParam Map<String,String> allRequestParam
    ) {
        String stringId = allRequestParam.get("employeeId");
        Long id = Long.valueOf(stringId);
        Employee inputEmployee = employeeDao.findEmployee(id);


        for (int i = 0; i <cust.size(); i++) {
            if (cust.get(i).getId() == inputEmployee.getId()){
                System.out.println("torol " +cust.get(i).getName());
                System.out.println(cust.size() +  "törlés elott");
                cust.remove(i);
                System.out.println(cust.size() + " törlés után");

            }
        }
        System.out.println(cust.toArray().toString());
        System.out.println("naa " + inputEmployee.getName());
//        System.out.println("naa " + allRequestParams.entrySet());
//        System.out.println("naa " + allRequestParams.get("janos"));
        // Create Response Object
        Response response = new Response("Done", inputEmployee.getName());
        return response;
    }

    @RequestMapping(value = "/restoreEmployee", method = RequestMethod.POST)
    @ResponseBody
    public String restoreEmployee(@RequestParam Map<String,String> allRequestParam){
        String name = allRequestParam.get("name");
        System.out.println("name "+ name);
        List<Employee> employees = employeeDao.getAllEmployee();
        for (int i = 0; i <employees.size(); i++) {
            if (employees.get(i).getName().equals(name)){
                cust.add(employees.get(i));
                System.out.println(cust.size() + " visszadas után");

            }
        }

        return name;
    }
    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    @ResponseBody
    public String postComment (@RequestParam Map<String,String> allRequestParam) {
        String comment = allRequestParam.get("comment");
        String role = allRequestParam.get("role");
        String eventid = allRequestParam.get("eventId");
        Event event = eventDao.findOne(Long.valueOf(eventid));
        User user = userDao.getUserByPosition(role);
        commentDao.saveComment(new Comment(comment,user,event));
        return comment;

    }
    public String getPrincipalRole(Principal principal){
       User user = userDao.getUserByEmailAddress(principal.getName());
       return user.getPosition();

    }

}
