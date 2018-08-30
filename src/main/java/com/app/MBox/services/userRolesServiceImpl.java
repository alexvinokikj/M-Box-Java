package com.app.MBox.services;

import com.app.MBox.core.model.userRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.MBox.core.repository.userRolesRepository;

import java.util.Set;

@Service("userRolesServiceImpl")
public class userRolesServiceImpl implements userRolesService {
    @Autowired
    private userRolesRepository userRolesRepository ;




    public userRoles findByUserId(Integer userId) {

        return userRolesRepository.findByUserId(userId);
    }

    public Set <userRoles> findByRoleId(Integer roleId) {

        return userRolesRepository.findByRoleId(roleId);
    }


    public void saveUserRoles(userRoles userRoles) {

        userRolesRepository.save(userRoles);
    }
}
