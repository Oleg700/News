package com.epam.news.service.user;

import com.epam.news.model.News;
import com.epam.news.model.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User add(User user);
}
