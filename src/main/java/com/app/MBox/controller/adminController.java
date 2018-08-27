package com.app.MBox.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class adminController {

@RequestMapping(value = "/admin")
public String admin() {

    return "adminDashboard";
}
}
