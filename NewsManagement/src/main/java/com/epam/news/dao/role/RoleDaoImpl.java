package com.epam.news.dao.role;

import com.epam.news.model.user.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role add(Role role) {
        return entityManager.merge(role);
    }

    @Override
    public Collection<Role> getAll() {
        return entityManager.createQuery("from Roles").getResultList();
    }

    @Override
    public Role getByName(String name) {
        return (Role) entityManager.createQuery("select r from Roles r where r.name = :name").setParameter("name", name).getSingleResult();
    }
}
