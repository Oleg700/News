package com.epam.news.service.user;

import com.epam.news.dao.user.UserDao;
import com.epam.news.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
    public User getByName(String name){
        return userDao.getByName(name);
    }

    @Override
    public User add(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        return userDao.add(user);
    }
}
