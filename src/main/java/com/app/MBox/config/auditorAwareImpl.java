package com.app.MBox.config;

import com.app.MBox.aditional.user;
import com.app.MBox.core.model.users;
import com.app.MBox.services.userServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class auditorAwareImpl implements AuditorAware<Integer> {
@Autowired
    userServiceImpl userServiceImpl;
    @Override
//    public Optional<Integer> getCurrentAuditor() {
//       String email= SecurityContextHolder.getContext().getAuthentication().getName();
//       users user=userServiceImpl.findByEmail(email);             //getting stuck hereeee ??????
//        if(user != null ) {
//            return Optional.of(user.getId());
//
//        }   else {
//            return Optional.of(-1);
//
//        }

    public Optional<Integer> getCurrentAuditor() {
        Object object= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(object instanceof user) {
            user user=(user)object;
            return Optional.of(user.getUserId());

        }   else {
            return Optional.of(-1);

        }






    }


}
