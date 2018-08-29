package com.app.MBox.services;


import com.app.MBox.core.model.role;
import com.app.MBox.core.model.users;
import com.app.MBox.core.model.userRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class userDetailsServiceImpl implements UserDetailsService {

    @Autowired
    userService userService;

    @Autowired
    roleService roleService;

    @Autowired
    userRolesService userRolesService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        users activeUser=userService.findByEmail(email);
            if (activeUser!=null && activeUser.isActivated()) {
                userRoles userRoles=userRolesService.findByUserId(activeUser.getId());
                GrantedAuthority authority =new SimpleGrantedAuthority(userRoles.getRole().getName());
                User user = new User (activeUser.getEmail(),activeUser.getPassword(), Arrays.asList(authority));
                UserDetails userDetails=(UserDetails) user;
                return user;
            }

            return null;                   //maybe it will show mistake because I return null
    }
}
