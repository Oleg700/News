package com.epam.news.service.role;

import com.epam.news.dao.role.RoleDao;
import com.epam.news.model.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * implementation of interface {@link RoleService}.
 *
 * @author Oleg Aliyev
 */
public class RoleServiceImpl implements RoleService {

    /**
     * roleDao is used to process privilege objects in database.
     */
    private RoleDao roleDao;

    /**
     * constructor.
     *
     * @param roleDao is used to process privilege objects in database.
     */
    @Autowired
    public RoleServiceImpl(final RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Collection<Role> getAll() {
        return roleDao.getAll();
    }

    @Override
    @Transactional
    public Role add(final Role role) {
        return roleDao.add(role);
    }
}
