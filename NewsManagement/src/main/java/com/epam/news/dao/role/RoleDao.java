package com.epam.news.dao.role;

import com.epam.news.model.Role;

public interface RoleDao {

    Role add(Role role);

    Role getByName(String name);
}
