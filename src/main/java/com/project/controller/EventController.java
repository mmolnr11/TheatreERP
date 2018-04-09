package com.project.controller;

import com.project.dao.CommentDao;
import com.project.dao.EmployeeDao;
import com.project.dao.EventDao;
//import com.project.dao.RoleDao;
import com.project.dao.UserDao;
import com.project.model.*;
//import com.project.model.Role;
import com.project.service.DateValidation;
import com.project.service.EmployeeService;
import com.project.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.text.ParseException;
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
    EventService eventService;
    @Autowired
    EmployeeService employeeService;

    @Autowired
    CommentDao commentDao;

//    @Autowired
//    RoleDao roleDao;

//    @PostMapping(value = "/event-detail")
//    public String vlami (Model model, @RequestParam("startdate") String startDate, @RequestParam("startdate") String endDate) throws ParseException {
////        String formatted = date.substring(0,10);
//        List<Date> dates = dateValidation.createDate(startDate,endDate);
//        List<Event> eventList =  eventDao.findByDate(dates.get(0),dates.get(1));
//        System.out.println(eventList.size());
//        model.addAttribute("eventList",eventList);
//        return "superadmin";
//    }

    @GetMapping(value = "/event/{id}/description")
    public String renderEventDetailsPage(Model model, @PathVariable("id") Long id){
        Event event = eventDao.findOne(id);
        model.addAttribute("event", event);
//        model.addAttribute("roles", )
        return "material-update";
    }

    @GetMapping(value = "/user/event/{id}/description")
    public String userEventDetailsPage(Principal principal, Model model, @PathVariable("id") Long id){
        Event event = eventDao.findOne(id);
        List detailsOfOrderedEmployees = eventService.gettingDesiredEmpNumberToOneEvent(event,principal);
        String roleCorrect = (String) detailsOfOrderedEmployees.get(0);
        Integer wantedNumberOfEmployees = (Integer) detailsOfOrderedEmployees.get(1);
        List<Employee> notYetOrderedEmployees = eventService.selectingNotOrderedEmployees(event, roleCorrect);
        int alreadyAssignedEmployees = event.getEmployeesToEvent().size();
        int actualNumberOfEmployees = wantedNumberOfEmployees - alreadyAssignedEmployees;

        model.addAttribute("event", event);
        model.addAttribute("roleString", roleCorrect);
        model.addAttribute("roleInteger", actualNumberOfEmployees);
        model.addAttribute("employees", notYetOrderedEmployees);
        return "user-event-detail-material";
    }





//    @PostMapping(value = "/event/{id}/update")
//    public String saveChangesToLiveshow (@ModelAttribute("event") Event event,
//                                         Model model, @PathVariable("id") Long id){
//        Event eventOld = eventDao.findOne(id);
//        Event updatedEvent = eventService.updateEvent(eventOld,event);
//        eventDao.saveEvent(updatedEvent);
//        return "superadmin";
//    }

//    @GetMapping(value = "/event/{id}/delete")
//    public String deleteEvent(@PathVariable("id") Long id){
//        eventDao.deleteOne(id);
//        return "superadmin";
//    }
//    @GetMapping(value = "/event/create")
//    public String renderCreateEventTemplate(Model model){
//        List<String> roles = employeeDao.getEmployeeRoles();
//        int numberOfRole = 0;
//        model.addAttribute("roles",roles );
//        model.addAttribute("numberOfRole",numberOfRole );
//        return "create-event";
//    }


//    @PostMapping(value = "/user/event-detail")
//    public String renderUserEvents (Model model, @RequestParam("startdate") String startDate,@RequestParam("enddate") String endDate) throws ParseException {
//        List<Date> dates = dateValidation.createDate(startDate, endDate);
//        List<Event> eventList =  eventDao.findByDate(dates.get(0),dates.get(1));
//        model.addAttribute("eventList",eventList);
//        return "user";}




    List<Employee> notYetOrderedEmployees = new ArrayList<Employee>();
    List<Employee> alreadyOrderedEmployees = new ArrayList<Employee>();


    @GetMapping(value="event/user/{id}/description")
    public String userGetEventDescription(Model model, @PathVariable("id") Long id, Principal principal){
        Event event = eventDao.findOne(id);
        List detailsOfOrderedEmployees = eventService.gettingDesiredEmpNumberToOneEvent(event,principal);
        String roleCorrect = (String) detailsOfOrderedEmployees.get(0);
        Integer wantedNumberOfEmployees = (Integer) detailsOfOrderedEmployees.get(1);

        List<Employee> notYetOrderedEmployees = eventService.selectingNotOrderedEmployees(event, roleCorrect);
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
    public String postEmployee(@RequestParam Map<String,String> allRequestParam) {
        String result = eventService.addingEmployeeToEvent(allRequestParam);
        return result;
//        String stringId = allRequestParam.get("employeeId");
//        String eventid = allRequestParam.get("eventId");
//        Event event = eventDao.findOne(Long.valueOf(eventid));
//
//        if (!stringId.equals("")){
//            Long id = Long.valueOf(stringId);
//            Employee inputEmployee = employeeDao.findEmployee(id);
//            addWorkingHoures(event, inputEmployee);
//
//            for (int i = 0; i <notYetOrderedEmployees.size(); i++) {
//                if (notYetOrderedEmployees.get(i).getId() == inputEmployee.getId()){
//                    alreadyOrderedEmployees.add(notYetOrderedEmployees.get(i));
//                    notYetOrderedEmployees.remove(i);
//
//                }
//            }
//            event.setEmployeesToEvent(alreadyOrderedEmployees);
//            eventDao.saveEvent(event);
//
//            Response response = new Response("Done", inputEmployee.getName());
//            return response;
//        }
//        else {
//            Response response = new Response("Done", "Kerlek addj hozza valakit");
//            return response;}

    }

    @RequestMapping(value = "/restoreEmployee", method = RequestMethod.POST)
    @ResponseBody
    public String restoreEmployee(@RequestParam Map<String,String> allRequestParam){
        String result = eventService.restoreEmployee(allRequestParam);
//        String name = allRequestParam.get("name");
//        String employeeId = allRequestParam.get("empId");
//        String eventId = allRequestParam.get("eventId");
//        Employee inputEmployee = employeeDao.findEmployee(Long.valueOf(employeeId));
//        Event event = eventDao.findOne(Long.valueOf(eventId));
//        List<Employee> employees = employeeDao.getAllEmployee();
//        List<Employee> alreadyOrderedEmployees = event.getEmployeesToEvent();
//        for (int i = 0; i <employees.size(); i++) {
//            if (employees.get(i).getName().equals(name)){
//                notYetOrderedEmployees.add(employees.get(i));
//                alreadyOrderedEmployees.remove(employees.get(i));
//            }
//        }
//        removeWorkingHoures(event,inputEmployee);
//
//        event.setEmployeesToEvent(alreadyOrderedEmployees);
//        eventDao.saveEvent(event);
        return result;
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


    @RequestMapping(value = "/event/add-event", method = RequestMethod.POST)
    @ResponseBody
    public List<String> addEventToDb(@RequestParam HashMap<String,String> allRequestParams) throws ParseException {
        Event event = eventService.createEvent(allRequestParams);
        List<String> errorMessages = eventService.validateEvent(event);
        if (errorMessages.size() == 0){
            eventDao.saveEvent(event);
        }
        return errorMessages;

    }
    //       return user.getPosition();
    //       User user = userDao.getUserByEmailAddress(principal.getName());
//    public String getPrincipalRole(Principal principal){

//    }

    //        return "material";
    //        eventDao.saveEvent(newEvent);
    //        Event newEvent = eventService.createEvent(dates,allRequestParams);
    //        List<Date> dates = dateValidation.createDateFromForm(allRequestParams);
    //    public String saveNewEvent (@RequestParam HashMap<String,String> allRequestParams) throws ParseException {
//    @PostMapping(value = "/event/create")

//    }

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
