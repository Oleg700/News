package com.epam.news.dao.role;

import com.epam.news.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role add(Role role) {
        return entityManager.merge(role);
    }

    @Override
    public Role getByName(String name) {
        return (Role) entityManager.createQuery("select r from Roles r where r.name = :name").setParameter("name",name).getSingleResult();
    }
}
