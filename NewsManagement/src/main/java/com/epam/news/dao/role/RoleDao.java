package com.epam.news.dao.role;

import com.epam.news.model.user.Role;

import java.util.Collection;

/**
 * Dao layer for database communication with entity {@link Role}.
 *
 * @author Oleg Aliyev
 */
public interface RoleDao {

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
