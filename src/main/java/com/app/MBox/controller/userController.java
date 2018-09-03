package com.app.MBox.controller;


import com.app.MBox.core.model.users;
import com.app.MBox.services.userServiceImpl;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.regex.Pattern;


@Controller
public class userController {
    @Autowired
    userServiceImpl userServiceImpl;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/changePassword",method = RequestMethod.GET)
    public ModelAndView showChangePassword(ModelAndView modelAndView) {
        modelAndView.setViewName("changePassword");
        return modelAndView;
    }

    @RequestMapping(value = "/changePassword",method = RequestMethod.POST)
    public ModelAndView processChangePassword(ModelAndView modelAndView, @RequestParam("password") String password,@RequestParam("newPassword") String newPassword,@RequestParam("confirmPassword") String confirmPassword) {
        String name=SecurityContextHolder.getContext().getAuthentication().getName();
        users user=userServiceImpl.findByEmail(name);
        if(!bCryptPasswordEncoder.matches(password,user.getPassword())) {
            modelAndView.addObject("oldPasswordErrorMessage","Incorrect password");
            modelAndView.setViewName("changePassword");
            return modelAndView;
        }   else if (invalidPassword(newPassword)) {
            modelAndView.addObject("errorMessage","Password must be between 8 and 64 characters and be alphanumeric");
            modelAndView.setViewName("changePassword");
            return modelAndView;
        }   else if (!passwordMatches(newPassword,confirmPassword)) {
            modelAndView.addObject("errorConfirmMessage","Password does not match");
            modelAndView.setViewName("changePassword");
            return modelAndView;
        }
        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        userServiceImpl.saveUser(user);
        modelAndView.setViewName("ConfirmationChangePassword");
        return modelAndView;

    }


    public boolean invalidPassword(String password) {

        if(password.length()<8 || password.length()>64) {
            return true;
        }   else if (!Pattern.compile("[a-zA-Z]").matcher(password).find()) {
            return true;
        }   else if(!Pattern.compile( "[0-9]" ).matcher( password ).find() && !Pattern.compile("[$&+,:;=?@#|'<>.-^*()%!]").matcher(password).find() ) {
            return true;
        }
        return false;

    }

    public boolean passwordMatches(String password,String confirmPassword) {

        if (password.equals(confirmPassword)) {
            return true;
        }   else {
            return false;
        }
    }

}
