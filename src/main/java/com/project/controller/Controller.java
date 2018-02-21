package com.project.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@org.springframework.stereotype.Controller
public class Controller {

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

}
