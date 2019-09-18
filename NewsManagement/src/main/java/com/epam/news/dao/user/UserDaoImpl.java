package com.epam.news.dao.user;

import com.epam.news.model.user.Role;
import com.epam.news.model.user.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAll() {
        List<User> newsList = entityManager.createNamedQuery("getAllUsers", User.class).getResultList();
        return newsList;
    }

    @Override
    public User getByName(String name) {
        User user = (User) entityManager.createNamedQuery("getUserByName", User.class).setParameter("name", name).getSingleResult();
        return (User) user;

    }

    @Override
    public User add(User user) {
        return entityManager.merge(user);
    }

    @Override
    public Collection<Role> getRoleByUsername(String username) {
        return getByName(username).getRoles();
    }
}
