package com.app.MBox.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class adminController {

@RequestMapping(value = "/dashboard")
public String showAdminDashboard() {

    return "adminDashboard";
}
}
