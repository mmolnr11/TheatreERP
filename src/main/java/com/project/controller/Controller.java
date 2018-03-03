package com.project.controller;

import com.project.dao.EventDao;
import com.project.dao.UserDao;
import com.project.model.Event;
import com.project.model.User;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    UserDao userDao;
    @Autowired
    EventDao eventDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String renderLoginPage(Model model) {
        return "index";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String superadmin(Model model) {
        return "superadmin";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String simauser(Model model) {
        return "simauser";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String renderIndex(){return "login";}

    @RequestMapping(value = "/add-new-user", method = RequestMethod.GET)
    public String renderAddNewUserTemplate(Model model){return "addnewuser";}

    @RequestMapping(value = "/add-new-user", method = RequestMethod.POST)
    public String saveNewUser(@RequestParam HashMap<String,String> allRequestParams, Model model) {
        User user = userDao.createUser(allRequestParams);
        userDao.saveUser(user);
        model.addAttribute("userDetails", user);
        return "user-details";}

    @RequestMapping(value = "/modify-user", method = RequestMethod.GET)
    public String modifyUserTemplate(Model model){
        List<User> users = userDao.findAll();
        model.addAttribute("allUsers", users);
        return "modify-user";}

    @GetMapping(value = "/{id}/modify")
    public String modifyUser (Model model, @PathVariable("id") Long id ){
        User user = userDao.findById(id);
        model.addAttribute("user", user);
        return "modify2";
    }

    @PostMapping(value = "/modify/{id}")
    public String saveModifiedUser (Model model, @RequestParam HashMap<String,String> allRequestParams,
                                    @PathVariable("id") Long id ){
        User user = userDao.updateUser(allRequestParams,id);
        userDao.saveUser(user);
        model.addAttribute("userDetails", user);
        return "user-details";

    }

}
