package com.epam.news.service.user;

import com.epam.news.dao.user.UserDao;
import com.epam.news.model.user.Role;
import com.epam.news.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User getByName(String name) {
        return userDao.getByName(name);
    }

    @Override
    @Transactional
    public User add(User user) {
        String hashPassword = encodePassword(user.getPassword());
        user.setPassword(hashPassword);
        return userDao.add(user);
    }

    @Override
    public Collection<Role> getRoleByUsername(String username) {

        return userDao.getRoleByUsername(username);
    }

    private String encodePassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hash = passwordEncoder.encode(password);
        return hash;
    }
}
