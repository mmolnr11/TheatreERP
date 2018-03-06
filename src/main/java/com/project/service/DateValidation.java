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

    public List<Date> createDate(String date) throws ParseException {
        Date mdate = new SimpleDateFormat("MM/dd/yyyy HH:mm").parse(date);

        String formattedDate1 = new SimpleDateFormat("dd/MM/yyyy 00:01").format(mdate);
        String formattedDate2 = new SimpleDateFormat("dd/MM/yyyy 23:59").format(mdate);

        Date start = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(formattedDate1);
        Date end = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(formattedDate2);
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
