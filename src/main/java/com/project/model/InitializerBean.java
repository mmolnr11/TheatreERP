package com.project.model;


import com.project.dao.*;
//import com.project.dao.RoleDao;
//import com.project.repository.RoleRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class InitializerBean {
    public InitializerBean(CommentDao commentDao,
                           DatesOfEventDao datesOfEventDao,
                           EmployeeDao employeeDao, UserDao userDao, EventDao eventDao, BCryptPasswordEncoder bCryptPasswordEncoder) throws ParseException {


        User user1 = new User("Szabo", "Andris", "andras.l.szabo@gmail.com", "pass", "admin", "Admin");
        User user2 = new User("Szabo", "Gyozo", "andras.gyogyo.szabo@gmail.com", "pass", "user", "Berendezo");
        Vilagosito vilagosito = new Vilagosito("Vilagosito", "Miska",32, "Vilagosito");
        Vilagosito vilagosito2 = new Vilagosito("Vilagosito", "Elek",32, "Vilagosito");
        Berendezo berendezo = new Berendezo("Berendez", "ELek",32, "Berendezo");
        Berendezo berendezo3 = new Berendezo("BerendezCsuka", "Monika",32, "Berendezo");
        Berendezo berendezo4 = new Berendezo("BerendezőStohl", "Andras",32, "Berendezo");
        Berendezo berendezo2 = new Berendezo("Megrendez", "Nelli",32, "Berendezo");

        SimpleDateFormat dateformat2 = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        String strdate1 = "03/04/2018 14:35";
        String strdate2 = "03/04/2018 18:50";
        String strdate3 = "03/10/2018 18:30";
        String strdate4 = "03/10/2018 15:20";
        String strdate5 = "03/12/2018 12:20";
        String strdate6 = "03/12/2018 14:40";

        Date newdate = dateformat2.parse(strdate1);
        Date newdate1 = dateformat2.parse(strdate2);
        Date newdate2 = dateformat2.parse(strdate3);
        Date newdate3 = dateformat2.parse(strdate4);
        Date newdate4 = dateformat2.parse(strdate5);
        Date newdate5 = dateformat2.parse(strdate6);


        Event event1 = new Event("3 vilagosito", "Lion king","terem", "proba");
        Event event2 = new Event("5 berendezo", "Songoku","terem", "eloadas");
        Event event3 = new Event("6 vilagosito", "Pinokkio","terem", "proba");

        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(berendezo2);
        employees.add(berendezo);

        user1.setPassword(bCryptPasswordEncoder.encode(user1.getPassword()));
        user2.setPassword(bCryptPasswordEncoder.encode(user2.getPassword()));

        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        hashMap.put("Berendezo", 2);
        hashMap.put("Vilagosito",2);
        event1.setEmployeesInNumbersToEvent(hashMap);
        event2.setEmployeesInNumbersToEvent(hashMap);
        event3.setEmployeesInNumbersToEvent(hashMap);



        DatesOfEvent datesOfEvent1 = new DatesOfEvent( event2,newdate4,newdate5);
//        datesOfEvent.setNumbersOfEmployeeByPosition(hashMap);
        DatesOfEvent datesOfEvent2 = new DatesOfEvent( event2,newdate3,newdate2);
        DatesOfEvent datesOfEvent3 = new DatesOfEvent( event1,newdate,newdate1);
        Comment comment1 = new Comment("andris szep gyerek",user2,datesOfEvent1);
        Comment comment2 = new Comment("csak sokat cigizik",user1,datesOfEvent1);

        List<Comment> comments = new ArrayList<>();
        comments.add(comment1);
        comments.add(comment2);

        List<DatesOfEvent> datesOfEventsList = new ArrayList<>();
        datesOfEventsList.add(datesOfEvent1);
        datesOfEventsList.add(datesOfEvent2);
        datesOfEventsList.add(datesOfEvent3);

        eventDao.saveEvent(event1);
        eventDao.saveEvent(event2);
        eventDao.saveEvent(event3);

        datesOfEvent1.setComments(comments);

        datesOfEvent1.addEmployee(berendezo);
        datesOfEvent1.addEmployee(berendezo2);
        datesOfEvent1.addEmployee(vilagosito);
        datesOfEvent1.addEmployee(vilagosito2);

//        datesOfEvent1.addEmployee(berendezo3);

//        employeeDao.saveEmployee(vilagosito2);
//        employeeDao.saveEmployee(vilagosito);
//        employeeDao.saveEmployee(berendezo);
//        employeeDao.saveEmployee(berendezo2);
        employeeDao.saveEmployee(berendezo3);
        employeeDao.saveEmployee(berendezo4);


        datesOfEventDao.saveDate(datesOfEvent1);
        datesOfEventDao.saveDate(datesOfEvent2);
        datesOfEventDao.saveDate(datesOfEvent3);

//TODO
        userDao.saveUser(user1);
        userDao.saveUser(user2);

        commentDao.saveComment(comment1);
        commentDao.saveComment(comment2);





















    }
}
