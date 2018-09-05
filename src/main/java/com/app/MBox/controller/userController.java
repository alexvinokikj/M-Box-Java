package com.app.MBox.controller;


import com.app.MBox.aditional.passwordChecker;
import com.app.MBox.aditional.properties;
import com.app.MBox.core.model.users;
import com.app.MBox.dto.changePasswordDto;
import com.app.MBox.dto.userDto;
import com.app.MBox.services.userServiceImpl;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.regex.Pattern;


@Controller
public class userController {
    @Autowired
    userServiceImpl userServiceImpl;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    properties properties;
    @Autowired
    passwordChecker passwordChecker;

    @RequestMapping(value = "/changePassword",method = RequestMethod.GET)
    public Model showChangePassword(Model model) {
        changePasswordDto changePasswordDto=new changePasswordDto();
        model.addAttribute("changePasswordDto",changePasswordDto);
        return model;
    }

    @RequestMapping(value = "/changePassword",method = RequestMethod.POST)
    public ModelAndView processChangePassword(@ModelAttribute("changePasswordDto") changePasswordDto changePasswordDto) {

        String name=SecurityContextHolder.getContext().getAuthentication().getName();
        users user=userServiceImpl.findByEmail(name);
        ModelAndView modelAndView=new ModelAndView();
        if(!bCryptPasswordEncoder.matches(changePasswordDto.getPassword(),user.getPassword())) {
            modelAndView.addObject("oldPasswordErrorMessage","Incorrect password");
            modelAndView.setViewName("changePassword");
            return modelAndView;
        }   else if (passwordChecker.isInvalidPassword(changePasswordDto.getNewPassword())) {
            modelAndView.addObject("errorMessage",properties.getPasswordMessage());
            modelAndView.setViewName("changePassword");
            return modelAndView;
        }   else if (!passwordChecker.doPasswordMatches(changePasswordDto.getNewPassword(),changePasswordDto.getConfirmPassword())) {
            modelAndView.addObject("errorConfirmMessage","Password does not match");
            modelAndView.setViewName("changePassword");
            return modelAndView;
        }
        user.setPassword(bCryptPasswordEncoder.encode(changePasswordDto.getPassword()));
        userServiceImpl.saveUser(user);
        modelAndView.setViewName("ConfirmationChangePassword");
        return modelAndView;

    }

}
