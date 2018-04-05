package com.project.service;

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

    public List<Date> createDateFromForm(HashMap<String, String> allRequestParams) throws ParseException {
        String eventDay = allRequestParams.get("startdate");
        String startTime = allRequestParams.get("startDateTime");
        Date newDate1 = new SimpleDateFormat("MM/dd/yyyy").parse(eventDay);

        String formattedDate1 = new SimpleDateFormat("dd/MM/yyyy "+ startTime).format(newDate1);

        Date eventStart = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(formattedDate1);
        String endTime = allRequestParams.get("endDateTime");
        Date newDate2 = new SimpleDateFormat("MM/dd/yyyy").parse(eventDay);

        String formattedDate2 = new SimpleDateFormat("dd/MM/yyyy "+ endTime).format(newDate2);

        Date eventEnd = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(formattedDate2);
        List<Date> list = new ArrayList<>();
        list.add(eventStart);
        list.add(eventEnd);
        return list;
    }
}
