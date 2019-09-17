package com.epam.news.dao.role;

import com.epam.news.model.user.Role;

import java.util.Collection;

import java.util.Collection;

public interface RoleDao {

    Collection<Role> getAll();

    Role getByName(String name);

    Role add(Role role);


}
