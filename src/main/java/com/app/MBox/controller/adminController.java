package com.app.MBox.controller;


import com.app.MBox.aditional.passwordChecker;
import com.app.MBox.aditional.properties;
import com.app.MBox.dto.recordLabelDto;
import com.app.MBox.services.recordLabelServiceImpl;
import com.app.MBox.services.userServiceImpl;
import com.app.MBox.services.verificationTokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/admin")
public class adminController  {

    @Autowired
    userServiceImpl userServiceImpl;
    @Autowired
    recordLabelServiceImpl recordLabelServiceImpl;
    @Autowired
    verificationTokenServiceImpl verificationTokenServiceImpl;
    @Autowired
    passwordChecker passwordChecker;
    @Autowired
    properties properties;


    @RequestMapping(value = "/dashboard" , method = RequestMethod.GET)
    public ModelAndView showAdminDashboard(Model model) {
    List<recordLabelDto> recordLabels=new LinkedList<>();
    recordLabels=userServiceImpl.findRecordLabels(0,20);
    model.addAttribute("recordLabels",recordLabels);
    ModelAndView modelAndView=new ModelAndView();
    modelAndView.setViewName("adminDashboard");
    return modelAndView;
}

    @RequestMapping(value = "/dashboard",method = RequestMethod.POST)
    public List<recordLabelDto> processLazyLoading(@RequestParam("val") int pageNumber) {
        System.out.println("I AM IN THE CONTROLLER");
        System.out.println(pageNumber + " THIS IS THE PAGE NUMBER");
        List<recordLabelDto> recordLabels=new LinkedList<>();
        recordLabels=userServiceImpl.findRecordLabels(pageNumber,20);
        return recordLabels;
    }

    @RequestMapping(value = "/addNewRecordLabel",method = RequestMethod.GET)
    public ModelAndView showAddNewRecordLabelForm() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("addNewRecordLabel");
        return modelAndView;
    }


    @RequestMapping(value = "/addNewRecordLabel" , method = RequestMethod.POST)
    public ModelAndView processAddNewRecordLabelForm(ModelAndView modelAndView,@RequestParam("name") String recordLabelName,@RequestParam("email") String recordLabelEmail,HttpServletRequest request) {
        System.out.println("NAME : " + recordLabelName + " EMAIL : " + recordLabelEmail);
        if(recordLabelName.length()<2 || recordLabelName.length()>50) {
            modelAndView.addObject("errorNameMessage","Name must be between 2 and 50 characters");
            modelAndView.setViewName("addNewRecordLabel");
            return modelAndView;
        }

        if(recordLabelEmail.length()>320) {
            modelAndView.addObject("errorNameMessage","Invalid email");
            modelAndView.setViewName("addNewRecordLabel");
            return modelAndView;
        }
        modelAndView.setViewName("confirmationAddNewRecordLabel");
        recordLabelServiceImpl.createRecordLabel(recordLabelName,recordLabelEmail,request);
        return modelAndView;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ModelAndView processDeleteRecordLabel(ModelAndView modelAndView,@RequestParam("email") String email) {
        recordLabelServiceImpl.deleteRecordLabel(email);
        modelAndView.setViewName("redirect:dashboard");
        return modelAndView;
        }





}
