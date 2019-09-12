package com.epam.news.service.role;

import com.epam.news.dao.role.RoleDao;
import com.epam.news.model.Role;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleServiceImpl implements RoleService{


    private RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role add(Role role) {
        return roleDao.add(role);
    }

    @Override
    public Role getByName(String name) {
        return roleDao.getByName(name);
    }
}
