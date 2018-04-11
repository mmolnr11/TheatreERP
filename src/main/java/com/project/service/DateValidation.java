package com.project.service;

import com.project.model.DatesOfEvent;
import com.project.model.Event;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class DateValidation {

    public List<Date> createDate(String startDate, String endDate) throws ParseException {
        Date date1 = new SimpleDateFormat("MM/dd/yyyy HH:mm").parse(startDate);
        Date date2 = new SimpleDateFormat("MM/dd/yyyy HH:mm").parse(endDate);

        String formattedDate1 = new SimpleDateFormat("dd/MM/yyyy 00:01").format(date1);
        String formattedDate2 = new SimpleDateFormat("dd/MM/yyyy 23:59").format(date2);

        Date start = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(formattedDate1);
        Date end = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(formattedDate2);
        List<Date> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        return list;
    }
    public List<Date> createDateNew (String startDate, String endDate) throws ParseException {
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
        Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);

        String formattedDate1 = new SimpleDateFormat("yyyy/MM/dd 00:01").format(date1);
        String formattedDate2 = new SimpleDateFormat("yyyy/MM/dd 23:59").format(date2);

        Date start = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(formattedDate1);
        Date end = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(formattedDate2);
        List<Date> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        return list;
    }
    public DatesOfEvent dateToEvent (Event event, String dayOfEvent, String startDate, String endDate) throws ParseException {
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(dayOfEvent);
        Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(dayOfEvent);

        String formattedDate1 = new SimpleDateFormat("yyyy/MM/dd " + startDate).format(date1);
        String formattedDate2 = new SimpleDateFormat("yyyy/MM/dd "+ endDate).format(date2);

        Date start = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(formattedDate1);
        Date end = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(formattedDate2);
        DatesOfEvent datesOfEvent = new DatesOfEvent(event, start,end);
//        List<Date> list = new ArrayList<>();
//        list.add(start);
//        list.add(end);
        return datesOfEvent;
    }

    public void createDateFromForm(HashMap<String, String> allRequestParams) throws ParseException {
//        String eventDay = allRequestParams.get("date");
//        String startTime = allRequestParams.get("startDateTime");
//        Date newDate1 = new SimpleDateFormat("yyyy-MM-dd").parse(eventDay);
////        Date newDate1 = new SimpleDateFormat("MM/dd/yyyy").parse(eventDay);
//
//        String formattedDate1 = new SimpleDateFormat("yyyy/MM/dd "+ startTime).format(newDate1);
//
//        Date eventStart = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(formattedDate1);
////        Date eventStart = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(formattedDate1);
//
//
//        String endTime = allRequestParams.get("endDateTime");
//        Date newDate2 = new SimpleDateFormat("yyyy-MM-dd").parse(eventDay);
//
//        String formattedDate2 = new SimpleDateFormat("yyyy/MM/dd "+ endTime).format(newDate2);
//
//        Date eventEnd = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(formattedDate2);
//        List<Date> list = new ArrayList<>();
//        list.add(eventStart);
//        list.add(eventEnd);
//        return list;
    }
}
