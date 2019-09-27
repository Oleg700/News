package com.epam.news.service.role;

import com.epam.news.model.user.Role;

import java.util.Collection;

/**
 * Service for wrapping {@link com.epam.news.dao.role.RoleDaoImpl}.
 *
 * <p>
 * Implementation {@link RoleServiceImpl}.
 * <p>
 *
 * @author Oleg Aliyev
 */
public interface RoleService {

    /**
     * Returns collection of all roles.
     *
     * @return collection of roles
     */
    Collection<Role> getAll();

    /**
     * saves role to database.
     *
     * @param role to save
     * @return saved role
     */
    Role add(Role role);


}
