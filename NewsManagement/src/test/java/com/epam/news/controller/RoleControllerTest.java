package com.epam.news.controller;

import com.epam.news.config.AppConfig;
import com.epam.news.model.user.Role;
import com.epam.news.service.role.RoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
class RoleControllerTest {

    @Autowired
    private RoleService roleService;

    @Test
    void getAllRoles() {
        List<Role> listRoles = (List<Role>) roleService.getAll();
        assertFalse(listRoles.isEmpty());
    }

    @Test
    void addRole() {
        Role role = new Role("ROLE_TEST");
        Role roleResult = roleService.add(role);
        assertNotNull(roleResult);
    }
}