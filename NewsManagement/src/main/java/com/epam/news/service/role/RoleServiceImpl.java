package com.epam.news.service.role;

import com.epam.news.dao.role.RoleDao;
import com.epam.news.model.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;


public class RoleServiceImpl implements RoleService {


    private RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Collection<Role> getAll() {
        return roleDao.getAll();
    }

    @Override
    @Transactional
    public Role add(Role role) {
        return roleDao.add(role);
    }
}
