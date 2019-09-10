package com.epam.news.service.privilege;

import com.epam.news.dao.privilege.PrivilegeDao;
import com.epam.news.model.Privilege;
import org.springframework.beans.factory.annotation.Autowired;

public class PrivilegeServiceImpl implements PrivilegeService{


    private PrivilegeDao privilegeDao;

    @Autowired
    public PrivilegeServiceImpl(PrivilegeDao privilegeDao) {
        this.privilegeDao = privilegeDao;
    }

    @Override
    public Privilege add(Privilege privilege) {
        return privilegeDao.add(privilege);
    }
}
