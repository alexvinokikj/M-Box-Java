package com.app.MBox.controller;

import com.app.MBox.Dto.userDto;
import com.app.MBox.core.model.*;
import com.app.MBox.services.userService;
import com.app.MBox.services.verificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;




@Controller
public class registerController {

    @Autowired
    private userService userService;

    @Autowired
    private verificationTokenService verificationTokenService;


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
        userDto userDto = new userDto();
        model.addAttribute("user", userDto);
        return "registration";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid userDto accountDto, BindingResult result, HttpServletRequest request, Errors errors) {
        users registered = new users();

        if (!result.hasErrors()) {

            registered = createUserAccount(accountDto, result , request);
        }
        if (registered == null) {
            result.rejectValue("email", "message.regError");
        }

        if (result.hasErrors()) {

            return new ModelAndView("registration", "user", accountDto);

        }
        else {
            return new ModelAndView("success", "user", accountDto);
        }
        // rest of the implementation
    }
    private users createUserAccount(userDto accountDto, BindingResult result, HttpServletRequest request) {
        users registered = null;
        try {
            registered = userService.registerNewUserAccount(accountDto,request);
        } catch (Exception e) {
            return null;
        }
        return registered;
    }

    @RequestMapping(value = "/successRegister")
    public String success () {

        return "successRegister";
    }


    @RequestMapping(value = "/confirm" , method = RequestMethod.GET)
    public String showConfirmationPage(@RequestParam("token") String token) {

        boolean result=verificationTokenService.confirmUser(token);
        if(result) {

            return "successfullConfirm";
        }   else {
                return "unSuccessfullConfirm";
        }


    }


}
