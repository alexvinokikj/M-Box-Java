package com.app.MBox.services;

import com.app.MBox.core.model.userRoles;
import com.app.MBox.core.model.users;
import com.app.MBox.core.model.verificationToken;
import com.app.MBox.core.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.MBox.core.repository.verificationTokenRepository;

import java.util.Calendar;
import java.util.Date;

@Service("verificationTokenServiceImpl")
public class verificationTokenServiceImpl implements verificationTokenService {
    @Autowired
    private verificationTokenRepository verificationTokenRepository ;

    @Autowired
    private userRepository userRepository;
    @Autowired
    private userRolesServiceImpl userRolesServiceImpl;


    public verificationToken findByUserId(int id) {

        return verificationTokenRepository.findByUserId(id);
    }

    public verificationToken findByVerificationToken(String token) {

        return verificationTokenRepository.findByToken(token);
    }

    public users findByToken(String token) {

        return verificationTokenRepository.findByToken(token).getUser();
    }


    public void saveVerificationToken(verificationToken verificationToken) {

        verificationTokenRepository.save(verificationToken);
    }

    public void delete(String token) {
        verificationToken verificationToken=findByVerificationToken(token);
        verificationTokenRepository.delete(verificationToken);
    }

    public boolean confirmUser(String token) {
        verificationToken verificationToken=verificationTokenRepository.findByToken(token);
        users user=verificationToken.getUser();
        Date today=new Date();
        Date dateCreated=verificationToken.getDateCreated();
        Date newDate=new Date(dateCreated.getTime()+24*3600*1000);// 24 hours * 3600 seconds * 1000 milisecond in every second

        if(today.compareTo(newDate)<=0) {
            user.setActivated(true);
            userRepository.save(user);
            verificationTokenRepository.delete(verificationToken);
             return true;
        }   else {
            userRoles userRoles=userRolesServiceImpl.findByUserId(user.getId());
           userRolesServiceImpl.deleteUserRoles(userRoles);
            verificationTokenRepository.delete(verificationToken);
           userRepository.delete(user);
                return false;
        }

    }

    public boolean confirmPasswordResetUser(String token) {
        verificationToken verificationToken=verificationTokenRepository.findByToken(token);
        Date today=new Date();
        Date dateCreated=verificationToken.getDateCreated();
        Date newDate=new Date(dateCreated.getTime()+24*3600*1000);// 24 hours * 3600 seconds * 1000 milisecond in every second

        if(today.compareTo(newDate)<=0) {
            return true;
        }   else {
            verificationTokenRepository.delete(verificationToken);
            return false;
        }

    }


}
