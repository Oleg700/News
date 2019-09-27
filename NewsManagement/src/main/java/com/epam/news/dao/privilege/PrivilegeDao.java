package com.epam.news.dao.privilege;

import com.epam.news.model.user.Privilege;

import java.util.Collection;

/**
 * Dao layer for database communication with entity {@link Privilege}.
 *
 * @author Oleg Aliyev
 */
public interface PrivilegeDao {

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
