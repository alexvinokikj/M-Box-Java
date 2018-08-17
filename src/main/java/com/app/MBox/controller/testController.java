package com.app.MBox.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testController {

@RequestMapping(value = "/hello")
public String test() {
    //System.out.println("something");
    return "test";
}

}
