package com.epam.news.service.role;

import com.epam.news.model.Role;

public interface RoleService {

    Role add(Role role);

    Role getByName(String name);
}
