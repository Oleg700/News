package com.epam.news.service.privilege;

import com.epam.news.model.Privilege;

import java.util.Collection;

public interface PrivilegeService {

    Collection<Privilege> getAll();

    Privilege getByName(String name);

    Privilege add(Privilege privilege);


}
