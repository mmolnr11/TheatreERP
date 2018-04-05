package com.project.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import com.project.dao.CommentDao;
import com.project.dao.EmployeeDao;
import com.project.dao.EventDao;
//import com.project.dao.RoleDao;
import com.project.dao.UserDao;
import com.project.model.*;
//import com.project.model.Role;
import com.project.service.DateValidation;
import com.project.service.EventValidation;
import netscape.security.UserTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
    EventValidation eventValidation;
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
//        model.addAttribute("event", new Event());
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
        return "material";
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




    List<Employee> notYetOrderedEmployees = new ArrayList<Employee>();
    List<Employee> alreadyOrderedEmployees = new ArrayList<Employee>();


    @GetMapping(value="event/user/{id}/description")
    public String vvv(Model model, @PathVariable("id") Long id, Principal principal){
        Event event = eventDao.findOne(id);
        Map<String, Integer> eployeesMap =event.getEventhezDolgozok();
        String role = getPrincipalRole(principal);
        String roleCorrect = "";
        Integer wantedNumberOfEmployees = 0;
        for (Map.Entry<String,Integer> entry : eployeesMap.entrySet())
            if(entry.getKey().equals(role)){
                roleCorrect = entry.getKey();
                wantedNumberOfEmployees = entry.getValue();
            }
        List<Employee> employees = employeeDao.getEmmployessByRoles(role);

        notYetOrderedEmployees = employees;
        alreadyOrderedEmployees = event.getEmployeesToEvent();
        for (int i = 0; i < alreadyOrderedEmployees.size(); i++) {
            for (int j = 0; j < notYetOrderedEmployees.size(); j++) {
                if (notYetOrderedEmployees.get(j).getId() == alreadyOrderedEmployees.get(i).getId()){
                    notYetOrderedEmployees.remove(j);

                }
            }

        }
        int alreadyAssignedEmployees = event.getEmployeesToEvent().size();
        int actualNumberOfEmployees = wantedNumberOfEmployees - alreadyAssignedEmployees;

        model.addAttribute("event", event);
        model.addAttribute("roleString", roleCorrect);
        model.addAttribute("roleInteger", actualNumberOfEmployees);
        model.addAttribute("employees", notYetOrderedEmployees);
        return "user-event-detail";
    }



    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
    @ResponseBody
    public Response postEmployee(@RequestParam Map<String,String> allRequestParam) {
        String stringId = allRequestParam.get("employeeId");
        String eventid = allRequestParam.get("eventId");
        Event event = eventDao.findOne(Long.valueOf(eventid));

        if (!stringId.equals("")){
            Long id = Long.valueOf(stringId);
            Employee inputEmployee = employeeDao.findEmployee(id);
            addWorkingHoures(event, inputEmployee);

            for (int i = 0; i <notYetOrderedEmployees.size(); i++) {
                if (notYetOrderedEmployees.get(i).getId() == inputEmployee.getId()){
                    alreadyOrderedEmployees.add(notYetOrderedEmployees.get(i));
                    notYetOrderedEmployees.remove(i);

                }
            }
            event.setEmployeesToEvent(alreadyOrderedEmployees);
            eventDao.saveEvent(event);
            for (Employee emp:alreadyOrderedEmployees
                 ) {            System.out.println("ezek vannak hozzarendelve "+ emp.getName());


            }
            Response response = new Response("Done", inputEmployee.getName());
            return response;
        }
        else {
            Response response = new Response("Done", "Kerlek addj hozza valakit");
            return response;}

    }

    @RequestMapping(value = "/restoreEmployee", method = RequestMethod.POST)
    @ResponseBody
    public String restoreEmployee(@RequestParam Map<String,String> allRequestParam){
        String name = allRequestParam.get("name");
        String employeeId = allRequestParam.get("empId");
        String eventId = allRequestParam.get("eventId");
        Employee inputEmployee = employeeDao.findEmployee(Long.valueOf(employeeId));
        Event event = eventDao.findOne(Long.valueOf(eventId));
        List<Employee> employees = employeeDao.getAllEmployee();
        List<Employee> alreadyOrderedEmployees = event.getEmployeesToEvent();
        for (int i = 0; i <employees.size(); i++) {
            if (employees.get(i).getName().equals(name)){
                notYetOrderedEmployees.add(employees.get(i));
                alreadyOrderedEmployees.remove(employees.get(i));
            }
        }
        removeWorkingHoures(event,inputEmployee);

        event.setEmployeesToEvent(alreadyOrderedEmployees);
        eventDao.saveEvent(event);
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
    private void addWorkingHoures(Event event, Employee employee) {
        long minutes = getDurationOfEvent(event);
        employee.setWorkingHours(minutes);
        employeeDao.saveEmployee(employee);

    }

    private long getDurationOfEvent(Event event) {
        Date startDate = event.getStartDateTime();
        Date endDate = event.getEndDateTime();
        long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
        long minutes = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return minutes;
    }

    private void removeWorkingHoures(Event event, Employee employee) {
        long minutes = getDurationOfEvent(event);
        employee.decreaseWorkingHours(minutes);
        employeeDao.saveEmployee(employee);

    }

    @RequestMapping(value = "/event/add-event", method = RequestMethod.POST)
    @ResponseBody
    public List<String> addEventToDb(@RequestParam HashMap<String,String> allRequestParams) throws ParseException {
        List<Date> dates = dateValidation.createDateFromForm(allRequestParams);
        Event newEvent = new Event(allRequestParams.get("description"),allRequestParams.get("title"),
                dates.get(0),dates.get(1),allRequestParams.get("location"),allRequestParams.get("type") );

        List<String> errorMessages = eventValidation.validateEvent(newEvent);
        if (errorMessages.size() == 0){
            eventDao.saveEvent(newEvent);
        }
        return errorMessages;

    }
//    @RequestMapping(value = "/event/search", method = RequestMethod.POST)
//    @ResponseBody
////    @JsonSerialize
//    public JSONObject searchEvent (
//            @RequestParam("searchdatestart") String startDate,
//            @RequestParam("searchdateend") String endDate) throws ParseException, JsonProcessingException {
//        List<Date> dates = dateValidation.createDateNew(startDate,endDate);
//        List<Event> eventList =  eventDao.findByDate(dates.get(0),dates.get(1));
//        System.out.println(eventList.size());
//        System.out.println(eventList.get(0).getTitle());
//        System.out.println(startDate + " pisti " + endDate);
//        System.out.println(dates.get(0).toString() + " pisti " + dates.get(0).toString());
//        Respons2 respons2 = new Respons2(eventList);
//        List<String> strings = new ArrayList<>();
//        strings.add("mlml");
//        strings.add("kam");
//        strings.add("lamx");
//
//        JSONObject responseDetailsJson = new JSONObject();
//        JSONArray jsonArray = new JSONArray();
//
//        for (int i = 0; i < eventList.size(); i++)
//        {
//            JSONObject formDetailsJson = new JSONObject();
//            formDetailsJson.put("id", eventList.get(i).getId());
//            formDetailsJson.put("title", eventList.get(i).getTitle());
//            formDetailsJson.put("description", eventList.get(i).getTitle());
//
//            jsonArray.put(formDetailsJson);
//        }
//        responseDetailsJson.put("events", jsonArray);
//        System.out.println(responseDetailsJson);
//
////        ObjectMapper objectMapper = new ObjectMapper();
////        String carAsString = "";
////        for (int i = 0; i < eventList.size(); i++) {
////             carAsString = objectMapper.writeValueAsString(eventList.get(i));
////
////        }
//////        objectMapper.writeValue(new File("target/car.json"), car);
////        System.out.println(carAsString);
//        return responseDetailsJson;
////        return eventList;
//    }
}
