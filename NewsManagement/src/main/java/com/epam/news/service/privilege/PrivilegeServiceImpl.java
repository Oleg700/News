package com.epam.news.service.privilege;

import com.epam.news.dao.privilege.PrivilegeDao;
import com.epam.news.model.Privilege;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

public class PrivilegeServiceImpl implements PrivilegeService{


    private PrivilegeDao privilegeDao;

    @Autowired
    public PrivilegeServiceImpl(PrivilegeDao privilegeDao) {
        this.privilegeDao = privilegeDao;
    }

    @Override
    @Transactional
    public Privilege add(Privilege privilege) {
        return privilegeDao.add(privilege);
    }

    @Override
    public Privilege getByName(String name) {
        return privilegeDao.getByName(name);
    }
}
