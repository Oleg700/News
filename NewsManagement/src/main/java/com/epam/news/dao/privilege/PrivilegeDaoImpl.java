package com.epam.news.dao.privilege;

import com.epam.news.model.Privilege;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PrivilegeDaoImpl implements PrivilegeDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Privilege add(Privilege privilege) {
        return entityManager.merge(privilege);
    }
}
