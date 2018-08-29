package com.app.MBox.services;

import com.app.MBox.core.model.users;

public interface userService {

    public users findByEmail(String email);

    public users saveUser(users user);

}
