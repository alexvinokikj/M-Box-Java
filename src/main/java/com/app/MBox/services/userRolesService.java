package com.app.MBox.services;

import com.app.MBox.core.model.userRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.MBox.core.repository.userRolesRepository;

@Service("userRolesService")
public class userRolesService {
    @Autowired
    private userRolesRepository userRolesRepository ;


   // public userRolesService (userRolesRepository userRolesRepository) {
        //this.userRolesRepository=userRolesRepository;
    //}

    public userRoles findByUserId(Integer userId) {

        return userRolesRepository.findByUserId(userId);
    }

    public userRoles findByRoleId(Integer roleId) {

        return userRolesRepository.findByRoleId(roleId);
    }


    public void saveUserRoles(userRoles userRoles) {

        userRolesRepository.save(userRoles);
    }
}
