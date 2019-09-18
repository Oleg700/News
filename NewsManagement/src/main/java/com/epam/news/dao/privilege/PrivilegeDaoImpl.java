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
        return entityManager.createNamedQuery("getAllPrivileges", Privilege.class)
                .getResultList();
    }



    @Override
    public Privilege add(Privilege privilege) {
        return entityManager.merge(privilege);
    }

}
