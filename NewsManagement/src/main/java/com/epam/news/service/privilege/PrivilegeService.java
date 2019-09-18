package com.epam.news.service.privilege;

import com.epam.news.model.user.Privilege;

import java.util.Collection;

public interface PrivilegeService {

    Collection<Privilege> getAll();

    Privilege add(Privilege privilege);


}
