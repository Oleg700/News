package com.epam.news.controller;

import com.epam.news.model.user.Role;
import com.epam.news.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@CrossOrigin("*")
@RestController
@EnableWebMvc
@RequestMapping(value = "/api")
public class RoleController {

    @Autowired
    private RoleService roleService;



    @PreAuthorize("hasAuthority('PRIVILEGE_READ_ROLE')")
    @GetMapping(value = "/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> listRoles = (List<Role>) roleService.getAll();
        return ResponseEntity.ok().body(listRoles);
    }


    @PreAuthorize("hasAuthority('PRIVILEGE_WRITE_ROLE')")
    @PostMapping(value = "/roles")
    public ResponseEntity<Role> addRole(@RequestBody Role role) {
        Role role1 = roleService.add(role);
        return ResponseEntity.ok().body(role1);
    }
}
