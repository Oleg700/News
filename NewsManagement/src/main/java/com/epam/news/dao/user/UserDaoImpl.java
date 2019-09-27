package com.epam.news.dao.user;

import com.epam.news.model.user.Role;
import com.epam.news.model.user.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;

/**
 * Implemetation of interface {@link UserDao}.
 *
 * @author Oleg Aliyev
 */
public class UserDaoImpl implements UserDao {

    /**
     * entity manager is used for communication with database.
     */
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAll() {
        List<User> newsList = entityManager.
                createNamedQuery("getAllUsers", User.class).getResultList();
        return newsList;
    }

    @Override
    public User getByName(final String name) {
        User user = (User) entityManager
                .createNamedQuery("getUserByName", User.class)
                .setParameter("name", name).getSingleResult();
        return (User) user;
    }

    @Override
    public User add(final User user) {
        return entityManager.merge(user);
    }

    @Override
    public Collection<Role> getRoleByUsername(final String username) {
        return getByName(username).getRoles();
    }
}
