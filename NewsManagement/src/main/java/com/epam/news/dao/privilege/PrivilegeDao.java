package com.epam.news.dao.privilege;

import com.epam.news.model.user.Privilege;

import java.util.Collection;


public interface PrivilegeDao {

    Collection<Privilege> getAll();

    Privilege add(Privilege privilege);


}
