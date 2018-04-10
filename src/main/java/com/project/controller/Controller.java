package com.project.controller;

import com.project.dao.EmployeeDao;
import com.project.dao.EventDao;
import com.project.dao.UserDao;
import com.project.model.Employee;
import com.project.model.Event;
import com.project.model.User;
import com.project.repository.UserRepository;
import com.project.service.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    UserDao userDao;
    @Autowired
    EventDao eventDao;
    @Autowired
    UserValidation userValidation;
    @Autowired
    EmployeeDao employeeDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String renderLoginPage(Model model, Principal principal ) {
        String role = getPrincipalRole(principal);
        System.out.println("EZ a role " + role);
        if(role.equals("ROLE_ADMIN")){
            return "redirect:/admin";
        }else if (role.equals("ROLE_USER")){
            return "redirect:/user";
        }else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String superadmin(Model model) {
        model.addAttribute("event", new Event());
        List<String> roles = employeeDao.getEmployeeRoles();
        model.addAttribute("roles", roles);

        return "material";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user(Model model) {
        return "user-material";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String renderIndex(){return "login";}

    @RequestMapping(value = "/add-new-user", method = RequestMethod.GET)
    public String renderAddNewUserTemplate(Model model){
        model.addAttribute("user", new User());
        return "addnewuser";}



    @RequestMapping(value = "/add-new-user", method = RequestMethod.POST)
    public String saveNewUser(@ModelAttribute("user") User user, Model model) {
        List<String> errorMessages = userValidation.validateRegistrationDatas(user,"majdPassword");
        String savingTried = "nemsikeres a mentes probald ujra";
        if (errorMessages.size() == 0){
            savingTried = "Sikeresen mentette a kovetkezo Usert";
            userDao.saveUser(userDao.createUser(user));
        }

        model.addAttribute("userDetails", user);
        model.addAttribute("errors", errorMessages);
        model.addAttribute("savingTried", savingTried);
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


    public String getPrincipalRole(Principal principal){
        User user = userDao.getUserByEmailAddress(principal.getName());
        return user.getPosition();

    }

    @GetMapping(value = "/log-out")
    public String logOut(Model model){
        return "index";
    }


    @GetMapping(value = "/error-page")
    public String errorPage(Model model) {
        return "error-page";

    }

}
