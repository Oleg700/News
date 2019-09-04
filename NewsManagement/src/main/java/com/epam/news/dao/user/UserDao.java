package com.epam.news.dao.user;

import com.epam.news.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAll();

    User add(User user);
}
