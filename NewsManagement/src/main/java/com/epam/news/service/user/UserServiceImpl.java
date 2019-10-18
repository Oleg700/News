package com.epam.news.service.user;

import com.epam.news.model.user.Role;
import com.epam.news.model.user.User;
import com.epam.news.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

/**
 * implementation of interface {@link UserService}.
 *
 * @author Oleg Aliyev
 */
@Service
public class UserServiceImpl implements UserService {

   private UserRepository userRepository;

    /**
     * @param userRepository userDao is used for connection with database.
     */
    @Autowired
    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User getByName(final String name) {
        return userRepository.findDistinctFirstByUsername(name);
    }

    @Override
    @Transactional
    public User save(final User user) {
        String hashPassword = encodePassword(user.getPassword());
        user.setPassword(hashPassword);
        return userRepository.save(user);
    }


    @Override
    public Collection<Role> getRolesByUsername(final String username) {
        /*return userDao.getRoleByUsername(username);*/
        return null;
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
