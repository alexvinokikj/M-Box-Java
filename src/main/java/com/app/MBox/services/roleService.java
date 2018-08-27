package com.app.MBox.services;

import com.app.MBox.core.model.role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.MBox.core.repository.roleRepository;

@Service("roleService")
public class roleService {
    @Autowired
    private roleRepository roleRepository ;


    //public roleService (roleRepository roleRepository) {
       // this.roleRepository=roleRepository;
    //}

    public role findByName(String name) {

        return roleRepository.findByName(name);
    }


    public void saveRole(role role) {

        roleRepository.save(role);
    }
}
