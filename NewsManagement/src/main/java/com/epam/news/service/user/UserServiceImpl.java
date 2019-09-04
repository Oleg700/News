package com.epam.news.service.user;

import com.epam.news.dao.user.UserDao;
import com.epam.news.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService{

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAll(){return userDao.getAll();}

    @Override
    public User add(User user) {
        return userDao.add(user);
    }
}
