package com.epam.news.model.repository;

import com.epam.news.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public class RoleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Role findByName(String name){
       return (Role) entityManager.createQuery("select c from Roles c where c.name = :name").setParameter("name",name).getSingleResult();
    }

    @Transactional
    public Role save(Role role){
        entityManager.merge(role);
        return role;
    }

}
