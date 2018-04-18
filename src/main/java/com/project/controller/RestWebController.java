package com.project.controller;

import com.project.dao.*;
import com.project.model.*;
import com.project.service.DateValidation;
import com.project.service.EventService;
import com.project.service.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class RestWebController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    EventDao eventDao;

    @Autowired
    DatesOfEventDao datesOfEventDao;

    @Autowired
    UserDao userDao;

    @Autowired
    UserValidation userValidation;

    @Autowired
    DateValidation dateValidation;

    @Autowired
    CommentDao commentDao;

    @Autowired
    EventService eventService;


    @RequestMapping(value = "/admin/event/addDate",method = RequestMethod.POST)
    public DatesOfEvent addDateToEvent(@RequestParam Map<String,String> allRequestParam) throws ParseException {
        String dayOfEvent = allRequestParam.get("dayOfEvent");
        String startTime = allRequestParam.get("startTime");
        String endTime = allRequestParam.get("endTime");
        String eventId = allRequestParam.get("eventId");
        Event event = eventDao.findOne(Long.valueOf(eventId));
        DatesOfEvent dates = dateValidation.dateToEvent(event, dayOfEvent,startTime,endTime);

        datesOfEventDao.saveDate(dates);
        return dates;


    }
    @RequestMapping(value = "/event/add-event", method = RequestMethod.POST)
    public List<String> addEventToDb(@RequestParam HashMap<String,String> allRequestParams) throws ParseException {
        Event event = eventService.createEvent(allRequestParams);
        List<String> errorMessages = eventService.validateEvent(event);
        if (errorMessages.size() == 0){
            eventDao.saveEvent(event);
        }
        return errorMessages;

    }
    // TODO update addEmployee

    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
    @ResponseBody
    public String postEmployee(@RequestParam Map<String,String> allRequestParam) {
        String result = eventService.addingEmployeeToEvent(allRequestParam);
        return result;
    }


    @RequestMapping(value = "/user/event/search", method = RequestMethod.POST)
    public List<SearchResult> searchAsUser (@RequestParam("searchdatestart") String startDate,
                                 @RequestParam("searchdateend") String endDate) throws ParseException {
        DatesOfEvent date = dateValidation.createDateNew(startDate,endDate);
        Timestamp timeStampStart = new Timestamp(date.getStartDate()
                .getTime());
        Timestamp timeStampEnd = new Timestamp(date.getEndDate()
                .getTime());

        List<SearchResult> talalt = new ArrayList<>();
        Map hashMap = new HashMap<String, DatesOfEvent>();


        for (Event event: eventDao.allEvent()
             ) {
            for (DatesOfEvent dateOfAnEvent: event.getDatesOfEvent()
                 ) {
                    if( dateOfAnEvent.getStartDate().after(timeStampStart)
                            &&
                            dateOfAnEvent.getEndDate().before(timeStampEnd)){
                        hashMap.put(event.getTitle(),dateOfAnEvent);
                        SearchResult searchResult =
                                new SearchResult(event,dateOfAnEvent);
                        talalt.add(searchResult);
                    }
            }
        }

        return talalt;
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

    @RequestMapping(value = "/admin/event/change", method = RequestMethod.POST)
    public Event changeEvent(@RequestParam HashMap<String,String> allRequestParam) throws ParseException {
        Event event = eventService.updateEvent(allRequestParam);
        return event;

    }


    @RequestMapping(value = "/deleteComment", method = RequestMethod.POST)
    public void deleteComment(@RequestParam("commentId") String commentId ){
        Long id = Long.valueOf(commentId);
        commentDao.deleteCommentByAdmin(id);
    }

    @RequestMapping(value = "/restoreEmployee", method = RequestMethod.POST)
    public String restoreEmployee(@RequestParam Map<String,String> allRequestParam){
        String result = eventService.restoreEmployee(allRequestParam);
        return result;
    }

    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public String postComment (@RequestParam Map<String,String> allRequestParam) {
        String comment = allRequestParam.get("comment");
        String role = allRequestParam.get("role");
        String dateId = allRequestParam.get("dateId");
        DatesOfEvent date = datesOfEventDao.findDate(Long.valueOf(dateId));
        User user = userDao.getUserByPosition(role);

        commentDao.saveComment(new Comment(comment,user,date));
        return comment;

    }

}