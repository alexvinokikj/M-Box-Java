package com.app.MBox.services;

import com.app.MBox.core.model.role;

public interface roleService {


    public role findByName(String name);

    public void saveRole(role role);

    public role findById(int id);
}
