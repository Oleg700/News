package com.epam.news.service.user;

import com.epam.news.dao.user.UserDao;
import com.epam.news.model.user.Role;
import com.epam.news.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

/**
 * implementation of interface {@link UserService}.
 *
 * @author Oleg Aliyev
 */
public class UserServiceImpl implements UserService {

    /**
     * userDao is used for connection with database.
     */
    private UserDao userDao;

    /**
     * @param userDao userDao is used for connection with database.
     */
    @Autowired
    public UserServiceImpl(final UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User getByName(final String name) {
        return userDao.getByName(name);
    }

    @Override
    @Transactional
    public User add(final User user) {
        String hashPassword = encodePassword(user.getPassword());
        user.setPassword(hashPassword);
        return userDao.add(user);
    }


    @Override
    public Collection<Role> getRoleByUsername(final String username) {
        return userDao.getRoleByUsername(username);
    }

    /**
     * Returns encoded password before saving in database.
     *
     * @param password user password for hashing
     * @return encoded password
     */
    private String encodePassword(final String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hash = passwordEncoder.encode(password);
        return hash;
    }
}
