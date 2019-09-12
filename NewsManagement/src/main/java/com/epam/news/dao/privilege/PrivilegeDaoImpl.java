package com.epam.news.dao.privilege;

import com.epam.news.model.Privilege;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public class PrivilegeDaoImpl implements PrivilegeDao {

    @PersistenceContext
    private EntityManager entityManager;



    @Override
    @Transactional
    public Privilege add(Privilege privilege) {
        return entityManager.merge(privilege);
    }

    @Override
    public Privilege getByName(String name) {
        return (Privilege) entityManager.createQuery("select p from Privileges p where p.name = :name").setParameter("name",name).getSingleResult();
    }
}
