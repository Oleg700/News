package com.epam.news.service.privilege;

import com.epam.news.dao.privilege.PrivilegeDao;
import com.epam.news.model.user.Privilege;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.Collection;

public class PrivilegeServiceImpl implements PrivilegeService {


    private PrivilegeDao privilegeDao;

    @Autowired
    public PrivilegeServiceImpl(PrivilegeDao privilegeDao) {
        this.privilegeDao = privilegeDao;
    }

    @Override
    public Collection<Privilege> getAll() {
        return privilegeDao.getAll();
    }


    @Override
    @Transactional
    public Privilege add(Privilege privilege) {
        return privilegeDao.add(privilege);
    }
}
