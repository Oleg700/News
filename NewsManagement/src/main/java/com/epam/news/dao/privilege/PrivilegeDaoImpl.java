package com.epam.news.dao.privilege;

import com.epam.news.model.user.Privilege;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

public class PrivilegeDaoImpl implements PrivilegeDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Collection<Privilege> getAll() {
        return entityManager.createQuery("from Privileges")
                .getResultList();
    }

    @Override
    public Privilege getByName(String name) {
        return (Privilege) entityManager.createQuery("select p from Privileges p where p.name = :name").setParameter("name", name).getSingleResult();
    }

    @Override
    public Privilege add(Privilege privilege) {
        return entityManager.merge(privilege);
    }

}
