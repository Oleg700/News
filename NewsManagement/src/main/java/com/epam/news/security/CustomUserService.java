package com.epam.news.security;

import com.epam.news.model.user.User;
import com.epam.news.model.user.UserPrincipal;
import com.epam.news.service.user.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Custom implementation of interface {@link UserDetailsService}
 * for retrieving {@link User} entity from database.
 *
 * @author Oleg Aliyev
 */

public class CustomUserService implements UserDetailsService {

    /**
     * userService is used to load username from database.
     */

    private UserService userService;

    /**
     * @param userService is used to load username from database.
     */

    public CustomUserService(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {
        User user = this.userService.getByName(username);
        return new UserPrincipal(user);
    }
}
