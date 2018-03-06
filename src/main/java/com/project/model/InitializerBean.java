package com.project.model;


import com.project.dao.EmployeeDao;
import com.project.dao.EventDao;
//import com.project.dao.RoleDao;
import com.project.dao.UserDao;
//import com.project.repository.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class InitializerBean {
    public InitializerBean(EmployeeDao employeeDao, UserDao userDao, EventDao eventDao) {


        User user = new User("Szabo", "Andris", "andras.l.szabo@gmail.com", "pass", "ROLE_ADMIN");
        User user2 = new User("Szabo", "Gyozo", "andras.gyogyo.szabo@gmail.com", "pass", "NEM_ADMIN");
        Vilagosito vilagosito = new Vilagosito("NEmSzabo", "NemGyozo",32);
        Vilagosito vilagosito2 = new Vilagosito("NEmSzabo2", "NemGyozo2",32);
        Berendezo berendezo = new Berendezo("Berendez", "ELek",32);
        Berendezo berendezo2 = new Berendezo("Megrendez", "Nelli",32);


        Event event1 = new Event("3 vilagosito", "Lion king", new Date(), new Date(),"terem", "proba");
        Event event2 = new Event("5 berendezo", "Songoku", new Date(), new Date(),"terem", "eloadas");
        Event event3 = new Event("6 vilagosito", "Pinokkio", new Date(), new Date(),"terem", "proba");


        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        hashMap.put("Berendezo", 4);
        hashMap.put("Vilagosito",2);
        event1.setEventhezDolgozok(hashMap);

        employeeDao.saveEmployee(vilagosito2);


        employeeDao.saveEmployee(vilagosito);

        employeeDao.saveEmployee(berendezo);

        employeeDao.saveEmployee(berendezo2);

        eventDao.saveEvent(event1);



        eventDao.saveEvent(event2);
        eventDao.saveEvent(event3);
        userDao.saveUser(user);
        userDao.saveUser(user2);


    }
}
