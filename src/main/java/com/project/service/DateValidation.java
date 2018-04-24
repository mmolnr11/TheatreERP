package com.project.service;

import com.project.dao.EventDao;
import com.project.model.DatesOfEvent;
import com.project.model.Event;
import com.project.model.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class DateValidation {

    @Autowired
    EventDao eventDao;

    public List<SearchResult> getDatesBetweenStartAndEnd(String startDate, String endDate ) throws ParseException {
        DatesOfEvent date = createDateNew(startDate,endDate);
        Timestamp timeStampStart = new Timestamp(date.getStartDate()
                .getTime());
        Timestamp timeStampEnd = new Timestamp(date.getEndDate()
                .getTime());

        List<SearchResult> result = new ArrayList<>();
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
                    result.add(searchResult);
                }
            }
        }
        return result;
    }
    public DatesOfEvent createDateNew (String startDate, String endDate) throws ParseException {
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
        Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);

        String formattedDate1 = new SimpleDateFormat("yyyy/MM/dd 00:01").format(date1);
        String formattedDate2 = new SimpleDateFormat("yyyy/MM/dd 23:59").format(date2);

        Date start = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(formattedDate1);
        Date end = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(formattedDate2);
        DatesOfEvent date = new DatesOfEvent(start,end);
        return date;
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
