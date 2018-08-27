package com.app.MBox.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testController {


@RequestMapping(value = "/test")
    public String test1() {
        //System.out.println("something");
        return "dropDownMenu";
    }

}
