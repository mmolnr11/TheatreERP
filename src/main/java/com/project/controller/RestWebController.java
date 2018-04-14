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

//    @RequestMapping(value = "/getallcustomer", method = RequestMethod.GET)

    //    public Response getResource() {
    //        Response response = new Response("Done", cust);
    //        return response;
    //    }
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    long id = 1;

    @RequestMapping(value = "/admin/event/addDate",method = RequestMethod.POST)
    public DatesOfEvent addDateToEvent(@RequestParam Map<String,String> allRequestParam) throws ParseException {
        String dayOfEvent = allRequestParam.get("dayOfEvent");
        String startTime = allRequestParam.get("startTime");
        String endTime = allRequestParam.get("endTime");
        String eventId = allRequestParam.get("eventId");
        Event event = eventDao.findOne(Long.valueOf(eventId));
        DatesOfEvent dates = dateValidation.dateToEvent(event, dayOfEvent,startTime,endTime);
//        List<DatesOfEvent> datesOfEvents = event.getDatesOfEvent();
//        datesOfEvents.add(dates);
        datesOfEventDao.saveDate(dates);
//        eventDao.saveEvent(event);
        return dates;


    }


//    @RequestMapping(value = "/event/search", method = RequestMethod.POST)
//    public List<Event> searchAsAdmin(@RequestParam("searchdatestart") String startDate,
//                                 @RequestParam("searchdateend") String endDate) throws ParseException {
//        List<Date> dates = dateValidation.createDateNew(startDate,endDate);
//        List<Event> eventList =  eventDao.findByDate(dates.get(0),dates.get(1));
//        return eventList;
//
//
//    }
//
    @RequestMapping(value = "/user/event/search", method = RequestMethod.POST)
    public List<Object[]> searchAsUser (@RequestParam("searchdatestart") String startDate,
                                 @RequestParam("searchdateend") String endDate) throws ParseException {
        DatesOfEvent date = dateValidation.createDateNew(startDate,endDate);
        List<Object[]> dateList =  datesOfEventDao.datesBetween(date.getStartDate(),date.getEndDate());
        List<Event> talalt = new ArrayList<>();
        for (Event event: eventDao.allEvent()
             ) {
            for (DatesOfEvent meglevodatumegyesemenynel: event.getDatesOfEvent()
                 ) {
                for (Object[] times: dateList
                     ) {
                    String start = String.valueOf(times[0]);
                    String end = String.valueOf(times[1]);
                    Date talaltDatumKezdete = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(start);
                    Date talaltDatumvege = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(end);
                    DatesOfEvent datumparAmimegfelelkeresesnek = new DatesOfEvent(talaltDatumKezdete,talaltDatumvege);
                    Timestamp timestampeleje = new Timestamp(talaltDatumKezdete.getTime());
                    Timestamp timestampvege = new Timestamp(talaltDatumvege.getTime());


                    if( timestampvege.equals(meglevodatumegyesemenynel.getEndDate())
                            && timestampeleje.equals(meglevodatumegyesemenynel.getStartDate())  ){
                        talalt.add(event);
                        System.out.println("egyezes van " + meglevodatumegyesemenynel.getStartDate()
                                + " " + meglevodatumegyesemenynel.getEndDate()
                                + " itt  " + event.getTitle()
                        );
                    }

                }

            }
        }
        for (Event event: talalt
             ) {
            System.out.println(event.getTitle() + " hppa");

        }
        return dateList;


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
    public Event changeEvent(@RequestParam HashMap<String,String> allRequestParam) throws ParseException {
        Event event = eventService.updateEvent(allRequestParam);

        return event;

    }
    @RequestMapping(value = "/deleteComment", method = RequestMethod.POST)
    public void deleteComment(@RequestParam("commentId") String commentId ){
        Long id = Long.valueOf(commentId);
        commentDao.deleteCommentByAdmin(id);
    }

}