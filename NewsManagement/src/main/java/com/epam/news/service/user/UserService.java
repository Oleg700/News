package com.epam.news.service.user;

import com.epam.news.model.user.Role;
import com.epam.news.model.user.User;

import java.util.Collection;
import java.util.List;

public interface UserService {

    List<User> getAll();

    User getByName(String name);

    Collection<Role> getRoleByUsername(String username);

    User add(User user);
}
