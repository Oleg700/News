package com.epam.news.controller;

import com.epam.news.config.AppConfig;
import com.epam.news.model.user.Privilege;
import com.epam.news.service.privilege.PrivilegeService;
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
class PrivilegeControllerTest {

    @Autowired
    private PrivilegeService privilegeService;

    @Test
    void getAllPrivileges() {
        List<Privilege> listPrivileges = (List<Privilege>) privilegeService.getAll();
        assertFalse(listPrivileges.isEmpty());
    }

    @Test
    void addPrivilege() {
        Privilege privilege = new Privilege("PRIVILEGE_TEST");
        Privilege privilegeResult = privilegeService.add(privilege);
        assertNotNull(privilegeResult);
    }
}