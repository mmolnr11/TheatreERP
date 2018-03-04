package com.project.model;


import com.project.dao.EmployeeDao;
import com.project.dao.EventDao;
import com.project.dao.UserDao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Component
public class InitializerBean {
    public InitializerBean(EmployeeDao employeeDao, UserDao userDao, EventDao eventDao) {
        User user = new User("Szabo", "Andris", "andras.l.szabo@gmail.com", "pass", "ROLE_ADMIN");
        User user2 = new User("Szabo", "Gyozo", "andras.gyogyo.szabo@gmail.com", "pass", "NEM_ADMIN");
        Vilagosito vilagosito = new Vilagosito("NEmSzabo", "NemGyozo",32, "vilagosit");
        Vilagosito vilagosito2 = new Vilagosito("NEmSzabo2", "NemGyozo2",32, "vilagosit");
        Berendezo berendezo = new Berendezo("Berendez", "ELek",32, "berendez");
        Berendezo berendezo2 = new Berendezo("Megrendez", "Nelli",32, "berendez");

        Event event1 = new Event("3 vilagosito", "Lion king", new Date(), new Date(),"terem", "proba");
        Event event2 = new Event("5 berendezo", "Songoku", new Date(), new Date(),"terem", "eloadas");
        Event event3 = new Event("6 vilagosito", "Pinokkio", new Date(), new Date(),"terem", "proba");
//        LiveShow liveShow2 = new LiveShow("3 vilagosito", "LionKing","Thu Feb 01 12:20:00 CET 2018","Thu Feb 01 20:00:00 CET 2018", "kozponti teem",true);

        System.out.println(" ez a date formatum " + event1.getStartDateTime());
        employeeDao.saveEmployee(vilagosito);
        employeeDao.saveEmployee(vilagosito2);
        employeeDao.saveEmployee(berendezo);
        employeeDao.saveEmployee(berendezo2);
        userDao.saveUser(user);
        userDao.saveUser(user2);
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(vilagosito);
        employeeList.add(vilagosito2);
        event1.setEventhezDolgozok(employeeList);
        eventDao.saveEvent(event1);
        eventDao.saveEvent(event2);
        eventDao.saveEvent(event3);

    }
}
