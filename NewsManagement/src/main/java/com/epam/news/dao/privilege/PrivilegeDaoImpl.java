package com.epam.news.dao.privilege;

import com.epam.news.model.user.Privilege;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

/**
 * Implemetation of interface {@link PrivilegeDao}.
 *
 * @author Oleg Aliyev
 */
public class PrivilegeDaoImpl implements PrivilegeDao {

    /**
     * entity manager is used for communication with database.
     */
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Collection<Privilege> getAll() {
        return entityManager
                .createNamedQuery("getAllPrivileges", Privilege.class)
                .getResultList();
    }


    @Override
    public Privilege add(final Privilege privilege) {
        return entityManager.merge(privilege);
    }

}
