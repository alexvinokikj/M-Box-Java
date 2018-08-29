package com.app.MBox.services;

import com.app.MBox.core.model.userRoles;

import java.util.Set;

public interface userRolesService {

    public userRoles findByUserId(Integer userId);

    public Set<userRoles> findByRoleId(Integer roleId);

    public void saveUserRoles(userRoles userRoles);

}
