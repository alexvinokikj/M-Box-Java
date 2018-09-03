package com.app.MBox.services;


import com.app.MBox.aditional.emailTemplateEnum;
import com.app.MBox.dto.userDto;
import com.app.MBox.core.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.app.MBox.core.repository.userRepository;
import com.app.MBox.aditional.rolesEnum;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service("userServiceImpl")
public class userServiceImpl implements userService {


    @Autowired
    private userRolesServiceImpl userRolesServiceImpl;
    @Autowired
    private userRepository userRepository ;

    @Autowired
    private verificationTokenServiceImpl verificationTokenServiceImpl;

    @Autowired
    private roleServiceImpl roleServiceImpl;

    @Autowired
    private emailTemplateService emailTemplateService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public users findByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    public users saveUser(users user) {

        return userRepository.save(user);
    }




    public users registerNewUserAccount(userDto accountDto,HttpServletRequest request) throws Exception {


            if(findByEmail(accountDto.getEmail())!=null) {
                throw new Exception(
                        "User already exists "
                                +  accountDto.getEmail());

            }

            users user=new users();
            user=createUser(accountDto);
            role role= roleServiceImpl.findByName(rolesEnum.LISTENER.toString());
            userRoles userRoles=new userRoles();
            userRoles.setUser(user);
            userRoles.setRole(role);
            userRolesServiceImpl.saveUserRoles(userRoles);

            verificationToken verificationToken=new verificationToken();
            verificationToken.setUser(user);
            verificationToken.setToken(UUID.randomUUID().toString());
            verificationTokenServiceImpl.saveVerificationToken(verificationToken);
            String appUrl=request.getScheme() + "://" + request.getServerName() +":8080"+ "/confirm?token=" + verificationToken.getToken(); //part :8080 wont be needed in stage

            emailTemplate emailTemplate=emailTemplateService.findByName(emailTemplateEnum.verificationMail.toString());
            String newBody=emailTemplate.getBody().replace("[NAME]",user.getName());
            String body=newBody.replaceAll("href=\"\"","href=\"" + appUrl + "\"");
           emailService emailService=new emailService();
            emailService.sendMail(user.getName(),user.getEmail(),emailTemplate.getSubject(),body);


        return user;
    }

    public users createUser(userDto accountDto) {
        users user=new users();
        user.setName(accountDto.getName());
        user.setEmail(accountDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(accountDto.getPassword()));
        user.setActivated(false);
        user=saveUser(user);
        return user;

    }


    public void forgotPassword(users user,HttpServletRequest request) {
        verificationToken verificationToken=new verificationToken();
        verificationToken.setUser(user);
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationTokenServiceImpl.saveVerificationToken(verificationToken);
        String appUrl=request.getScheme() + "://" + request.getServerName()+":8080" + "/resetPassword?token=" + verificationToken.getToken();
        emailTemplate emailTemplate=emailTemplateService.findByName(emailTemplateEnum.ForgotPassword.toString());
        String newBody=emailTemplate.getBody().replace("[NAME]",user.getName());
        String body=newBody.replace("[APPURL]",appUrl);
        emailService emailService=new emailService();
        emailService.sendMail(user.getName(),user.getName(),emailTemplate.getSubject(),body);

    }

    public void savePassword(String password, String token) {
            users user=verificationTokenServiceImpl.findByToken(token);
            user.setPassword(bCryptPasswordEncoder.encode(password));
            saveUser(user);
            verificationTokenServiceImpl.delete(token);
    }
}

