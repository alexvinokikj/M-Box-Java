package com.app.MBox.services;


import com.app.MBox.aditional.emailTemplateEnum;
import com.app.MBox.aditional.properties;
import com.app.MBox.dto.recordLabelDto;
import com.app.MBox.dto.userDto;
import com.app.MBox.core.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.app.MBox.core.repository.userRepository;
import com.app.MBox.aditional.rolesEnum;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

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
    @Autowired
    private properties properties;
    @Autowired
    private recordLabelArtistsServiceServiceImpl recordLabelArtistsServiceImpl;
    @Autowired
    private recordLabelServiceImpl recordLabelServiceImpl;
    @Autowired
    private emailService emailService;

    public users findByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    public users saveUser(users user) {

        return userRepository.save(user);
    }




    public users registerNewUserAccount(userDto accountDto,HttpServletRequest request){


            if(findByEmail(accountDto.getEmail())!=null) {
                return null;

            }

            users user=createUser(accountDto);
            role role= roleServiceImpl.findByName(rolesEnum.LISTENER.toString());
            userRoles userRoles=new userRoles();
            userRoles.setUser(user);
            userRoles.setRole(role);
            userRolesServiceImpl.saveUserRoles(userRoles);
            verificationToken verificationToken=verificationTokenServiceImpl.createToken(user);
            String appUrl=String.format("%s://%s%sconfirm?token=%s",request.getScheme(),request.getServerName(),properties.getPORT(),verificationToken.getToken());
            List <String> list=parsingEmailBody(user,appUrl,emailTemplateEnum.verificationMail.toString());
            emailService.sendMail(user.getName(),user.getEmail(),list.get(1),list.get(0));

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



    public void savePassword(String password, String token) {
            users user=verificationTokenServiceImpl.findByToken(token);
            user.setPassword(bCryptPasswordEncoder.encode(password));
            saveUser(user);
            verificationTokenServiceImpl.delete(token);
    }


    public boolean forgotPassword(String email,HttpServletRequest request) {
        users user=findByEmail(email);
        if(user!=null && user.isActivated()) {
            verificationToken verificationToken=verificationTokenServiceImpl.createToken(user);
            String appUrl=String.format("%s://%s%sresetPassword?token=%s",request.getScheme(),request.getServerName(),properties.getPORT(),verificationToken.getToken());
            List <String> list=parsingEmailBody(user,appUrl,emailTemplateEnum.forgotPassword.toString());
            emailService.sendMail(user.getName(),user.getEmail(),list.get(1),list.get(0));
             return true;

        }

        return false;

    }

    public List<String> parsingEmailBody(users user, String appUrl, String templateName) {
        emailTemplate emailTemplate=emailTemplateService.findByName(templateName);
        String newBody=emailTemplate.getBody().replace(properties.getNAME(),user.getName());
        String newBody1=newBody.replace(properties.getEMAILADRESS(),user.getEmail());
        String body=newBody1.replace(properties.getAPPURL(),appUrl);
        List <String> list=new LinkedList<>();
        list.add(body);
        list.add(emailTemplate.getSubject());
        return list;
    }

    public List<recordLabelDto> findRecordLabels(int page,int size) {
        List<recordLabelDto> recordLabelDtos=new LinkedList<>();
        List<users> users=userRepository.findRecordLabels(PageRequest.of(page,size));
        for(int i=0 ; i<users.size() ; i++) {
            recordLabelDto recordLabelDto=new recordLabelDto();
            recordLabelDto.setEmail(users.get(i).getEmail());
            recordLabelDto.setName(users.get(i).getName());
            recordLabel recordLabel=recordLabelServiceImpl.findByUserId(users.get(i).getId());
            int number= recordLabelArtistsServiceImpl.findNumberOfArtistsInRecordLabel(recordLabel.getId());
            recordLabelDto.setNumber(number);
            recordLabelDtos.add(recordLabelDto);
        }
        return recordLabelDtos;
    }
}

