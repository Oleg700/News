package com.epam.news.service.role;

import com.epam.news.model.user.Role;

import java.util.Collection;

public interface RoleService {

    Collection<Role> getAll();

    Role add(Role role);


}
