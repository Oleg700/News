package com.epam.news.dao.user;

import com.epam.news.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAll(){
        List<User> newsList = entityManager.createQuery("from Users").getResultList();
        return newsList;
    }

    @Override
    @Transactional
    public User add(User user) {
        return entityManager.merge(user);
    }

}
