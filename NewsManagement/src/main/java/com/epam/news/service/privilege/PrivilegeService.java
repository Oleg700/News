package com.epam.news.service.privilege;

import com.epam.news.model.user.Privilege;

import java.util.Collection;

/**
 * Service for wrapping {@link com.epam.news.dao.privilege.PrivilegeDaoImpl}.
 *
 * <p>
 * Implementation {@link PrivilegeServiceImpl}.
 * <p>
 *
 * @author Oleg Aliyev
 */
public interface PrivilegeService {

    /**
     * Returns collection of privileges.
     *
     * @return collection of privileges
     */
    Collection<Privilege> getAll();

    /**
     * saves privilege to database.
     *
     * @param privilege to save
     * @return saved privilege
     */
    Privilege add(Privilege privilege);


}
