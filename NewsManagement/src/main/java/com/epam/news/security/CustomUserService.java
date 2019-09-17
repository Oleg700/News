package com.epam.news.security;

import com.epam.news.model.user.MyUserPrincipal;
import com.epam.news.model.user.User;
import com.epam.news.service.user.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserService implements UserDetailsService {


    private UserService userService;

    public CustomUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userService.getByName(username);
        return new MyUserPrincipal(user);
    }
}
