package com.app.MBox.controller;

import com.app.MBox.core.model.users;
import com.app.MBox.services.userServiceImpl;
import com.app.MBox.services.verificationTokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.regex.Pattern;


@Controller
public class loginController {

    @Autowired
    userServiceImpl userServiceImpl;
    @Autowired
    verificationTokenServiceImpl verificationTokenServiceImpl;

    @GetMapping(value = "/login")
    public ModelAndView login() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/error")
    public ModelAndView error() {
        ModelAndView modelAndView=new ModelAndView();
        String errorMessage="You are not authorize for the requested data";
        modelAndView.addObject("errorMsg",errorMessage);
        modelAndView.setViewName("error");

        return modelAndView;

    }
    @GetMapping("/forgotPassword")
    public ModelAndView forgotPasswordGet() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("forgotPassword");
        return modelAndView;
    }


    @RequestMapping(value = "/forgotPassword",method = RequestMethod.POST)
    public ModelAndView forgotPasswordPost(ModelAndView modelAndView, @RequestParam("email") String userEmail, HttpServletRequest request) {
        users user=userServiceImpl.findByEmail(userEmail);

        if(user!=null && user.isActivated()) {
                userServiceImpl.forgotPassword(user,request);
                modelAndView.setViewName("passwordResetMail");
                return modelAndView;

        }   else {
            String errorMessage="Incorrect email";
            modelAndView.addObject("errorMsg",errorMessage);
            modelAndView.setViewName("forgotPassword");
            return modelAndView;
        }


    }


    @RequestMapping(value = "/resetPassword",method = RequestMethod.GET)
    public ModelAndView showResetPassword(ModelAndView modelAndView, @RequestParam("token") String token) {

        if (!verificationTokenServiceImpl.confirmPasswordResetUser(token)) {
            modelAndView.setViewName("error");                       //create error page for all the errors meaning all the token expired
            return modelAndView;
        }   else {
            modelAndView.addObject("confirmationToken",token);
            modelAndView.setViewName("resetPassword");
            return modelAndView;
        }


    }

    @RequestMapping(value = "/resetPassword",method = RequestMethod.POST)
    public ModelAndView processResetPassword(ModelAndView modelAndView, BindingResult bindingResult, @RequestParam("token") String token,@RequestParam("password") String password,@RequestParam("ConfirmPassword") String confirmPassword) {

        if(invalidPassword(password)) {
            modelAndView.addObject("errorMessage","Password must be between 8 and 64 characters and be alphanumeric");
            modelAndView.setViewName("redirect:resetPassword?token=" + token);
            return modelAndView;
        }   else if (!passwordMatches(password,confirmPassword)) {
            modelAndView.addObject("errorConfirmMessage","Passwords does not match");
            modelAndView.setViewName("redirect:resetPassword?token=" + token);
            return modelAndView;

        }
            userServiceImpl.savePassword(password,token);
            modelAndView.setViewName("confirmationPasswordReset");
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
