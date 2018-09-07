package com.app.MBox.services;

import com.app.MBox.aditional.emailTemplateEnum;
import com.app.MBox.aditional.properties;
import com.app.MBox.aditional.rolesEnum;
import com.app.MBox.core.model.*;
import com.app.MBox.core.repository.recordLabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service("recordLabelServiceImpl")
public class recordLabelServiceImpl implements recordLabelService {

    @Autowired
    recordLabelRepository recordLabelRepository;
    @Autowired
    userServiceImpl userServiceImpl;
    @Autowired
    roleServiceImpl roleServiceImpl;
    @Autowired
    userRolesServiceImpl userRolesServiceImpl;
    @Autowired
    verificationTokenServiceImpl verificationTokenServiceImpl;
    @Autowired
    artistServiceImpl artistServiceImpl;
    @Autowired
    emailTemplateService emailTemplateService;
    @Autowired
    properties properties;

    public recordLabel findByUserId(int userId) {
        return recordLabelRepository.findByUserId(userId);
    }

    public recordLabel saveRecordLabel(recordLabel recordLabel) {

        return recordLabelRepository.save(recordLabel);
    }



    public recordLabel createRecordLabel(String name,String email,HttpServletRequest request) {
        //create record label generate token and send mail
        users user=createUser(name,email);
        recordLabel recordLabel=new recordLabel();
        recordLabel.setUser(user);
        recordLabel=saveRecordLabel(recordLabel);
        verificationToken verificationToken=verificationTokenServiceImpl.createToken(user);

        String appUrl=String.format("%s://%s%sjoinIfInvited?token=%s",request.getScheme(),request.getServerName(),properties.getPORT(),verificationToken.getToken()); //part :8080 wont be needed in stage
        List<String> list=userServiceImpl.parsingEmailBody(user,appUrl,emailTemplateEnum.recordLabelSignUp.toString());
        emailService emailService=new emailService();
        emailService.sendMail(user.getName(),user.getEmail(),list.get(1),list.get(0));

        return recordLabel;
    }


    public users createUser(String name,String email) {
        // create new user and add role
        users user=new users();
        user.setEmail(email);
        user.setName(name);
        user.setActivated(false);
        user=userServiceImpl.saveUser(user);
        role role=roleServiceImpl.findByName(rolesEnum.RECORDLABEL.toString());
        userRoles userRoles=new userRoles();
        userRoles.setRole(role);
        userRoles.setUser(user);
        userRolesServiceImpl.saveUserRoles(userRoles);
        return user;
    }


    public void setRecordLabelPassword(String token, String password) {
        users user=verificationTokenServiceImpl.findByToken(token);
        user.setActivated(true);
        user.setPassword(password);
        userServiceImpl.saveUser(user);
        verificationTokenServiceImpl.delete(token);
    }

    public void deleteRecordLabel(String email) {
        //delete record label send email that is deleted find all his artist delete them and send them emails and delete record label user roles
        users user=userServiceImpl.findByEmail(email);
        recordLabel recordLabel=findByUserId(user.getId());
        List<artist> artists=artistServiceImpl.findAllArtists(recordLabel.getId());
        emailService emailService=new emailService();
        for (artist artist:artists) {
            artist.setDeleted(true);
            emailTemplate emailTemplate=emailTemplateService.findByName(emailTemplateEnum.deleteArtistMail.toString());
            String body=emailTemplate.getBody().replace(properties.getNAME(),user.getName());
            emailService.sendMail("MBox",artist.getUser().getEmail(),emailTemplate.getSubject(),body);  //only for testing properties.getToEmailAdress()
            artistServiceImpl.save(artist);
        }
        emailTemplate emailTemplate=emailTemplateService.findByName(emailTemplateEnum.deleteRecordLabelMail.toString());
        String body=emailTemplate.getBody().replace(properties.getNAME(),user.getName());
        emailService.sendMail("MBox",user.getEmail(),emailTemplate.getSubject(),body);                  //only for testing properties.getToEmailAdress()
        userRoles userRoles=userRolesServiceImpl.findByUserId(user.getId());
            if(userRoles!=null) {
                userRolesServiceImpl.deleteUserRoles(userRoles);
            }
        recordLabelRepository.delete(recordLabel);

        // Null exception because the record labels are not created by the rules meaning directly in the database
    }
}
