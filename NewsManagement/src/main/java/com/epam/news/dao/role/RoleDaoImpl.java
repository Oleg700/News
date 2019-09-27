package com.epam.news.dao.role;

import com.epam.news.model.user.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

/**
 * Implemetation of interface {@link RoleDao}.
 *
 * @author Oleg Aliyev
 */
public class RoleDaoImpl implements RoleDao {

    /**
     * entity manager is used for communication with database.
     */
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role add(final Role role) {
        return entityManager.merge(role);
    }

    @Override
    public Collection<Role> getAll() {
        return entityManager.
                createNamedQuery("getAllRoles", Role.class).getResultList();
    }

}
