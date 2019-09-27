package com.epam.news.service.privilege;

import com.epam.news.dao.privilege.PrivilegeDao;
import com.epam.news.model.user.Privilege;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * implementation of interface {@link PrivilegeService}.
 *
 * @author Oleg Aliyev
 */
public class PrivilegeServiceImpl implements PrivilegeService {

    /**
     * privilegeDao is used to process privilege objects in database.
     */
    private PrivilegeDao privilegeDao;

    /**
     * constructor.
     *
     * @param privilegeDao is used to process privilege objects in database.
     */
    @Autowired
    public PrivilegeServiceImpl(final PrivilegeDao privilegeDao) {
        this.privilegeDao = privilegeDao;
    }

    @Override
    public Collection<Privilege> getAll() {
        return privilegeDao.getAll();
    }


    @Override
    @Transactional
    public Privilege add(final Privilege privilege) {
        return privilegeDao.add(privilege);
    }
}
