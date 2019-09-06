package com.epam.news.model.repository;

import com.epam.news.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(User user){
        entityManager.persist(user);
    }
}
