package com.project.controller;

import com.project.dao.EventDao;
import com.project.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class EventController {
    @Autowired
    EventDao eventDao;

    @PostMapping(value = "/event-detail")
    public String vlami (Model model, @RequestParam("startdate") String date) throws ParseException {
//        String formatted = date.substring(0,10);
        System.out.println("input " +date);
        Date mdate = new SimpleDateFormat("MM/dd/yyyy HH:mm").parse(date);

        String formattedDate1 = new SimpleDateFormat("dd/MM/yyyy 00:01").format(mdate);
        String formattedDate2 = new SimpleDateFormat("dd/MM/yyyy 23:59").format(mdate);

        System.out.println("mdate " + mdate);
        Date te = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(formattedDate1);
        Date t2 = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(formattedDate2);
        System.out.println("ujra date "  + te);
        List<Event> eventList =  eventDao.findByDate(te,t2);
        System.out.println(eventList.size());
        model.addAttribute("proba",eventList);
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
        return "create-event";
    }
    @PostMapping(value = "/event/create")
    public String saveNewEvent (@RequestParam HashMap<String,String> allRequestParams) throws ParseException {

        String eventDay = allRequestParams.get("startdate");
        String startTime = allRequestParams.get("startDateTime");
        Date newDate1 = new SimpleDateFormat("MM/dd/yyyy").parse(eventDay);

        String formattedDate1 = new SimpleDateFormat("dd/MM/yyyy "+ startTime).format(newDate1);

        Date eventStart = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(formattedDate1);
        String endTime = allRequestParams.get("endDateTime");
        Date newDate2 = new SimpleDateFormat("MM/dd/yyyy").parse(eventDay);

        String formattedDate2 = new SimpleDateFormat("dd/MM/yyyy "+ endTime).format(newDate2);

        Date eventEnd = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(formattedDate2);



        Event newEvent = new Event(allRequestParams.get("description"),allRequestParams.get("title"),
                eventStart,eventEnd,allRequestParams.get("location"),allRequestParams.get("type") );
        System.out.println(newEvent.toString());
        eventDao.saveEvent(newEvent);
        return "superadmin";
    }
}
