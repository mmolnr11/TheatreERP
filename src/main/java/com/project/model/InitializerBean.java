package com.project.model;


import com.project.dao.CommentDao;
import com.project.dao.EmployeeDao;
import com.project.dao.EventDao;
//import com.project.dao.RoleDao;
import com.project.dao.UserDao;
//import com.project.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class InitializerBean {
    public InitializerBean(CommentDao commentDao, EmployeeDao employeeDao, UserDao userDao, EventDao eventDao, BCryptPasswordEncoder bCryptPasswordEncoder) throws ParseException {


        User user1 = new User("Szabo", "Andris", "andras.l.szabo@gmail.com", "pass", "admin");
        User user2 = new User("Szabo", "Gyozo", "andras.gyogyo.szabo@gmail.com", "pass", "user", "Berendezo");
        Vilagosito vilagosito = new Vilagosito("Kiss", "Miska",32, "Vilagosito");
        Vilagosito vilagosito2 = new Vilagosito("Nagy", "Elek",32, "Vilagosito");
        Berendezo berendezo = new Berendezo("Berendez", "ELek",32, "Berendezo");
        Berendezo berendezo3 = new Berendezo("Csuka", "Monika",32, "Berendezo");
        Berendezo berendezo4 = new Berendezo("Stohl", "Andras",32, "Berendezo");
        Berendezo berendezo2 = new Berendezo("Megrendez", "Nelli",32, "Berendezo");

        SimpleDateFormat dateformat2 = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        String strdate1 = "03/04/2018 14:35";
        String strdate2 = "03/04/2018 18:50";
        String strdate3 = "03/10/2018 13:30";
        String strdate4 = "03/10/2018 15:20";
        String strdate5 = "03/12/2018 12:20";
        String strdate6 = "03/12/2018 14:40";

        Date newdate = dateformat2.parse(strdate1);
        Date newdate1 = dateformat2.parse(strdate2);
        Date newdate2 = dateformat2.parse(strdate3);
        Date newdate3 = dateformat2.parse(strdate4);
        Date newdate4 = dateformat2.parse(strdate5);
        Date newdate5 = dateformat2.parse(strdate6);


        Event event1 = new Event("3 vilagosito", "Lion king", newdate, newdate1,"terem", "proba");
        Event event2 = new Event("5 berendezo", "Songoku", newdate2, newdate3,"terem", "eloadas");
        Event event3 = new Event("6 vilagosito", "Pinokkio", newdate4,newdate5,"terem", "proba");

        berendezo.setWorkingHours(event1.getDurationOfEvent());
        berendezo2.setWorkingHours(event1.getDurationOfEvent());
        List<Employee> employees = new ArrayList<>();
        employees.add(berendezo2);
        employees.add(berendezo);
        event1.setEmployeesToEvent(employees);
        event2.setEmployeesToEvent(employees);
        Comment comment1 = new Comment("andris szep gyerek",user2,event1);
        Comment comment2 = new Comment("csak sokat cigizik",user1,event1);
//        comments.add(comment1);
//        comments.add(comment2);
//        event1.setComments(comments);




        user1.setPassword(bCryptPasswordEncoder.encode(user1.getPassword()));
        user2.setPassword(bCryptPasswordEncoder.encode(user2.getPassword()));

        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        hashMap.put("Berendezo", 4);
        hashMap.put("Vilagosito",2);
        event1.setEventhezDolgozok(hashMap);
        event2.setEventhezDolgozok(hashMap);
        event3.setEventhezDolgozok(hashMap);

        employeeDao.saveEmployee(vilagosito2);


        employeeDao.saveEmployee(vilagosito);

        employeeDao.saveEmployee(berendezo);

        employeeDao.saveEmployee(berendezo2);
        employeeDao.saveEmployee(berendezo3);
        employeeDao.saveEmployee(berendezo4);


        eventDao.saveEvent(event1);
        eventDao.saveEvent(event2);
        eventDao.saveEvent(event3);


        userDao.saveUser(user1);
        userDao.saveUser(user2);
        commentDao.saveComment(comment1);
        commentDao.saveComment(comment2);


    }
}
