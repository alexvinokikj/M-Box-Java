package com.app.MBox.config;

import com.app.MBox.core.model.users;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class auditorAwareImpl implements AuditorAware<Integer> {

    //It is not correct first get the name of logged in user than find his id in the database
    @Override
    public Optional<Integer> getCurrentAuditor() {
       Object object= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(object  instanceof users ) {
            users user=(users) object;
            return Optional.of(user.getId());

        }   else {
            return Optional.of(-1);

        }


    }


}
