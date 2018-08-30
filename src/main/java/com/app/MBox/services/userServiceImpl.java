package com.app.MBox.services;


import com.app.MBox.dto.userDto;
import com.app.MBox.core.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.app.MBox.core.repository.userRepository;
import com.app.MBox.aditional.rolesEnum;

import javax.servlet.http.HttpServletRequest;

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
                        "There is an account with that email address: "
                                +  accountDto.getEmail());

            }

            //generate token and add user roles
            users user=new users();
            user=createUser(accountDto);
            role role= roleServiceImpl.findByName(rolesEnum.LISTENER.toString());
            userRoles userRoles=new userRoles();
            userRoles.setUser(user);
            userRoles.setRole(role);
            userRolesServiceImpl.saveUserRoles(userRoles);

//            verificationToken verificationToken=new verificationToken();
//            verificationToken.setUser(user);
//            verificationToken.setToken(UUID.randomUUID().toString());
//            verificationTokenServiceImpl.saveVerificationToken(verificationToken);
//            String appUrl=request.getScheme() + "://" + request.getServerName();
//
//            emailTemplate emailTemplate=emailTemplateService.findByName("verificationMail");
//            String body=emailTemplate.getBody();
//            String [] parts=body.split("<");
//            body="Dear " + user.getName() + "\n";
//                for (int i=1 ; i<parts.length ; i++) {
//                    if(i==parts.length-2) {
//                        body=body + parts[i] + appUrl + "/confirm?token=" + verificationToken.getToken() + "\n";
//                    }   else {
//
//                        body = body + parts[i] + "\n";
//                    }
//                }
//
//
//            SimpleMailMessage registrationEmail = new SimpleMailMessage();
//            registrationEmail.setTo(user.getEmail());
//            registrationEmail.setSubject(emailTemplate.getSubject());
//            registrationEmail.setText(body);
//            registrationEmail.setFrom("MBox@domain.com");
//
//            emailService.sendEmail(registrationEmail);


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
}

