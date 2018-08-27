package com.app.MBox.aditional;

import com.app.MBox.Dto.userDto;
import com.app.MBox.core.model.users;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

public interface IUserService  {

    users registerNewUserAccount(userDto accountDto,HttpServletRequest request)
            throws Exception;

}
