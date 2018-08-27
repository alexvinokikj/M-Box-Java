package com.app.MBox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class loginController {

    @RequestMapping(value = "/login")
    public String login() {

        return "login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
}
