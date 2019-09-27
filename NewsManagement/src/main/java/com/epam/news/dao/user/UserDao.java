package com.epam.news.dao.user;

import com.epam.news.model.user.Role;
import com.epam.news.model.user.User;

import java.util.Collection;
import java.util.List;

/**
 * Dao layer for database communication with entity {@link User}.
 *
 * @author Oleg Aliyev
 */
public interface UserDao {

    /**
     * Returns list of users.
     *
     * @return list of users
     */
    List<User> getAll();

    /**
     * Returns role by username.
     *
     * @param username of user to get list of roles
     * @return role
     */
    Collection<Role> getRoleByUsername(String username);

    /**
     * Returns user by username.
     *
     * @param name of user
     * @return user
     */
    User getByName(String name);

    /**
     * Returns added user.
     *
     * @param user to add
     * @return user
     */
    User add(User user);


}
