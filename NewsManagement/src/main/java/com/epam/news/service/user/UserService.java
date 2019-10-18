/**
 * package .service.user contains service
 * layer classes for processing entity user
 */
package com.epam.news.service.user;

import com.epam.news.model.user.Role;
import com.epam.news.model.user.User;

import java.util.Collection;
import java.util.List;

/**
 * Service for wrapping {@link com.epam.news.dao.user.UserDaoImpl}.
 *
 * <p>
 * Implementation {@link UserService}.
 * <p>
 *
 * @author Oleg Aliyev
 */
public interface UserService {

    /**
     * Returns list of users.
     *
     * @return list of users
     */
    List<User> findAll();

    /**
     * Returns user by username.
     *
     * @param name of user
     * @return user
     */
    User getByName(String name);

    /**
     * Returns role by username.
     *
     * @param username of user to get list of roles
     * @return role
     */
    Collection<Role> getRolesByUsername(String username);

    /**
     * Returns added user.
     *
     * @param user to add
     * @return user
     */
    User save(User user);
}
