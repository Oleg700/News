package com.epam.news.dao.user;

import com.epam.news.model.user.Role;
import com.epam.news.model.user.User;

import java.util.Collection;
import java.util.List;

public interface UserDao {

    List<User> getAll();

    User getByName(String name);

    User add(User user);

    Collection<Role> getRoleByUsername(String username);
}
