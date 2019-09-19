package com.epam.news.controller;

import com.epam.news.config.AppConfig;
import com.epam.news.model.user.Role;
import com.epam.news.model.user.User;
import com.epam.news.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
class UserControllerTest {

    private final static String USERNAME = "admin";
    private final static String USERNAME_TEST = "test";
    private final static String PASSWORD_TEST = "test";


    @Autowired
    private UserService userService;

    @Test
    void getRoleByUsername() {
        Collection<Role> roles = userService.getRoleByUsername(USERNAME);
        assertFalse(roles.isEmpty());
    }

    @Test
    void getAllUsers() {
        Collection<User> users = userService.getAll();
        assertFalse(users.isEmpty());
    }

    @Test
    void addUser() {
        User user = new User(USERNAME_TEST, PASSWORD_TEST);
        User userResult = userService.add(user);
        assertNotNull(userResult);
    }
}