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

import java.util.*;

@Component
public class InitializerBean {
    public InitializerBean(CommentDao commentDao, EmployeeDao employeeDao, UserDao userDao, EventDao eventDao, BCryptPasswordEncoder bCryptPasswordEncoder) {


        User user1 = new User("Szabo", "Andris", "andras.l.szabo@gmail.com", "pass", "admin");
        User user2 = new User("Szabo", "Gyozo", "andras.gyogyo.szabo@gmail.com", "pass", "user", "Berendezo");
        Vilagosito vilagosito = new Vilagosito("NEmSzabo", "NemGyozo",32, "Vilagosito");
        Vilagosito vilagosito2 = new Vilagosito("NEmSzabo2", "NemGyozo2",32, "Vilagosito");
        Berendezo berendezo = new Berendezo("Berendez", "ELek",32, "Berendezo");
        Berendezo berendezo2 = new Berendezo("Megrendez", "Nelli",32, "Berendezo");


        Event event1 = new Event("3 vilagosito", "Lion king", new Date(), new Date(),"terem", "proba");
        Event event2 = new Event("5 berendezo", "Songoku", new Date(), new Date(),"terem", "eloadas");
        Event event3 = new Event("6 vilagosito", "Pinokkio", new Date(), new Date(),"terem", "proba");
//        List<Comment> comments = new ArrayList<>();
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

        employeeDao.saveEmployee(vilagosito2);


        employeeDao.saveEmployee(vilagosito);

        employeeDao.saveEmployee(berendezo);

        employeeDao.saveEmployee(berendezo2);


        eventDao.saveEvent(event1);
        eventDao.saveEvent(event2);
        eventDao.saveEvent(event3);


        userDao.saveUser(user1);
        userDao.saveUser(user2);
        commentDao.saveComment(comment1);
        commentDao.saveComment(comment2);


    }
}
