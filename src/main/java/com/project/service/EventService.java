package com.project.service;

import com.project.dao.DatesOfEventDao;
import com.project.dao.EmployeeDao;
import com.project.dao.EventDao;
import com.project.dao.UserDao;
import com.project.model.DatesOfEvent;
import com.project.model.Employee;
import com.project.model.Event;
import com.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class EventService {
    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    UserDao userDao;

    @Autowired
    DatesOfEventDao datesOfEventDao;

    @Autowired
    EventDao eventDao;

    @Autowired
    DateValidation dateValidation;

    public List<String> validateEvent(Event event) {
        List<String> errorMessages = new ArrayList<>();

        if (event.getTitle().length() < 2 ) {
            errorMessages.add("Cim legyen több mint 2 karakter hosszú.");
        }
        if(event.getTitle().length() >0 && dataNotStartsWithUpperCaseLetter(event.getTitle())) {
            errorMessages.add("Cím kezdődjön nagy betűvel.");
        }


        return errorMessages;
    }

    private boolean dataNotStartsWithUpperCaseLetter(String word) {
        return Character.isLowerCase(word.charAt(0));
    };

    public List gettingDesiredEmpNumberToOneEvent(Event event, Principal principal) {

        Map<String, Integer> eployeesMap =event.getEmployeesInNumbersToEvent();
        String role = getPrincipalRole(principal);
        Integer wantedNumberOfEmployees = 0;
        String roleCorrect = "";
        List detailsToEmpAssignement = new ArrayList();
        for (Map.Entry<String,Integer> entry : eployeesMap.entrySet()){
            if(entry.getKey().equals(role)){
                roleCorrect = entry.getKey();
                wantedNumberOfEmployees = entry.getValue();
                detailsToEmpAssignement.add(roleCorrect);
                detailsToEmpAssignement.add(wantedNumberOfEmployees);
            }
        }
        return detailsToEmpAssignement;
    }

    public String getPrincipalRole(Principal principal){
        User user = userDao.getUserByEmailAddress(principal.getName());
        return user.getPosition();

    }

    public String addingEmployeeToEvent(Map<String, String> allRequestParam) {
        String stringId = allRequestParam.get("employeeId");
        String dateId = allRequestParam.get("dateId");
        DatesOfEvent date = datesOfEventDao.findDate(Long.valueOf(dateId));

        if (!stringId.equals("")){
            Long id = Long.valueOf(stringId);
            Employee inputEmployee = employeeDao.findEmployee(id);
            date.addEmployee(inputEmployee);
            datesOfEventDao.saveDate(date);
            return inputEmployee.getName();
        }
        else {
            return "Kerlek addj hozza valakit";
        }

    }

    public String restoreEmployee(Map<String, String> allRequestParam) {
        String name = allRequestParam.get("name");
        String employeeId = allRequestParam.get("empId");
        String eventId = allRequestParam.get("dateId");
        Employee inputEmployee = employeeDao.findEmployee(Long.valueOf(employeeId));
        DatesOfEvent date = datesOfEventDao.findDate(Long.valueOf(eventId));
        date.removeEmployee(inputEmployee);
        datesOfEventDao.saveDate(date);
        return name;
    }

    public Event createEvent(HashMap<String, String> allRequestParams) throws ParseException {
        Event newEvent = new Event(allRequestParams.get("description"),allRequestParams.get("title")
                ,allRequestParams.get("location"),allRequestParams.get("type") );
        String serialize = allRequestParams.get("serialize");

        Map<String, Integer> mapping = new HashMap<String, Integer>() {
        };
        for (String retval: serialize.split("&")) {
            int indexOf = retval.indexOf("=");
            String key = retval.substring(0,indexOf);
            String valueAsString = retval.substring(indexOf+1);
            for (String role: employeeDao.getEmployeeRoles()
                    ) {
                if(role.equals(key)){
                    Integer value = Integer.valueOf(valueAsString);
                    mapping.put(key,value);
                }
            }
        }
        newEvent.setEmployeesInNumbersToEvent(mapping);
        return newEvent;
    }
    public Event updateEvent(HashMap<String, String> allRequestParams) throws ParseException {
        String serialize = allRequestParams.get("serialize");

        Long id = Long.valueOf(allRequestParams.get("id"));
        Event event = eventDao.findOne(id);
        event.setTitle(allRequestParams.get("title"));
        event.setDescription(allRequestParams.get("description"));
        event.setLocation(allRequestParams.get("location"));
        event.setType(allRequestParams.get("type"));
        Map<String, Integer> alreadyAssignedNumbersToEvent = event.getEmployeesInNumbersToEvent();

//        Map<String, Integer> mapping = new HashMap<String, Integer>() {
//        };
        for (String retval: serialize.split("&")) {
            int indexOf = retval.indexOf("=");
            String key = retval.substring(0,indexOf);
            String valueAsString = retval.substring(indexOf+1);
            for (String role: employeeDao.getEmployeeRoles()
                    ) {
                if(role.equals(key)){
                    Integer value = Integer.valueOf(valueAsString);
                    alreadyAssignedNumbersToEvent.put(key,value);
                }
            }
        }
        event.setEmployeesInNumbersToEvent(alreadyAssignedNumbersToEvent);
        eventDao.saveEvent(event);
        return event;
    }

    public List<Employee> getAssignedEmployeesToDate(String positionOfUser,DatesOfEvent datesOfEvent) {
        List<Employee> allAlreadyAssignedEmployees = datesOfEvent.getEmployeesOfDates();
        List<Employee> employeesSortedByPosition = new ArrayList<>();
        for (Employee emp: allAlreadyAssignedEmployees
             ) {
            if (emp.getPosition().equals(positionOfUser)) {
                employeesSortedByPosition.add(emp);
            }
        }
        return employeesSortedByPosition;
    }


    public int getOrderedNumberByAdmin(Event event, String roleCorrect) {
        Map<String, Integer> map = event.getEmployeesInNumbersToEvent();
        int assignedNumber = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet())
        {
            if(entry.getKey().equals(roleCorrect)){
                assignedNumber = entry.getValue();
            }
        }
        return assignedNumber;
    }

    public List<Employee> getNotYetAssigned(List<Employee> alreadyAssignedToDateSorted, List<Employee> allEmployeesByPosition) {
        List<Employee> notAssignedEmployees = allEmployeesByPosition;


        for (int i = 0; i < allEmployeesByPosition.size(); i++) {
            for (int j = 0; j < alreadyAssignedToDateSorted.size(); j++) {
                if (allEmployeesByPosition.get(i).getName().equals(alreadyAssignedToDateSorted.get(j).getName())){
                    notAssignedEmployees.remove(allEmployeesByPosition.get(i));
                }
            }
        }
        return notAssignedEmployees;
    }
}
