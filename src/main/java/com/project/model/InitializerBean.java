package com.project.model;


import com.project.dao.EmployeeDao;
import com.project.dao.EventDao;
import com.project.dao.RoleDao;
import com.project.dao.UserDao;
import com.project.repository.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Component
public class InitializerBean {
    public InitializerBean(EmployeeDao employeeDao, UserDao userDao, EventDao eventDao, RoleDao roleDao) {

        Role role2 = new Role("vilagosito");
        Role role1 = new Role("berendezo");

        User user = new User("Szabo", "Andris", "andras.l.szabo@gmail.com", "pass", "ROLE_ADMIN");
        User user2 = new User("Szabo", "Gyozo", "andras.gyogyo.szabo@gmail.com", "pass", "NEM_ADMIN");
        Vilagosito vilagosito = new Vilagosito("NEmSzabo", "NemGyozo",32, role1);
        Vilagosito vilagosito2 = new Vilagosito("NEmSzabo2", "NemGyozo2",32, role1);
        Berendezo berendezo = new Berendezo("Berendez", "ELek",32, role2);
        Berendezo berendezo2 = new Berendezo("Megrendez", "Nelli",32, role2);




        Event event1 = new Event("3 vilagosito", "Lion king", new Date(), new Date(),"terem", "proba");
        Event event2 = new Event("5 berendezo", "Songoku", new Date(), new Date(),"terem", "eloadas");
        Event event3 = new Event("6 vilagosito", "Pinokkio", new Date(), new Date(),"terem", "proba");

        System.out.println(" ez a date formatum " + event1.getStartDateTime());
        System.out.println("4 hiba");
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(vilagosito);
        employeeList.add(vilagosito2);
        System.out.println("1 hiba");
        event1.setEventhezDolgozok(employeeList);
        roleDao.saveRole(role1);
        roleDao.saveRole(role2);
        System.out.println("5 hiba");
        employeeDao.saveEmployee(vilagosito2);


        employeeDao.saveEmployee(vilagosito);

        employeeDao.saveEmployee(berendezo);

        employeeDao.saveEmployee(berendezo2);

        System.out.println("2 hiba");
        eventDao.saveEvent(event1);



        System.out.println("6 hiba");

        System.out.println("3 hiba");

        eventDao.saveEvent(event2);
        eventDao.saveEvent(event3);
        userDao.saveUser(user);
        userDao.saveUser(user2);


    }
}
