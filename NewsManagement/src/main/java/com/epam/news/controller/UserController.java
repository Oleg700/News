package com.epam.news.controller;

import com.epam.news.model.user.Role;
import com.epam.news.model.user.User;
import com.epam.news.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Collection;
import java.util.List;

@CrossOrigin("*")
@RestController
@EnableWebMvc
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/users/{username}/roles")
    public ResponseEntity<Collection<Role>> getRoleByUsername(@PathVariable("username") String username) {
        Collection<Role> roles = userService.getRoleByUsername(username);
        return ResponseEntity.ok().body(roles);
    }



    @PreAuthorize("hasAuthority('PRIVILEGE_READ_USER')")
    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> newsList = userService.getAll();
        return ResponseEntity.ok().body(newsList);
    }


    @PreAuthorize("hasAuthority('PRIVILEGE_WRITE_USER')")
    @PostMapping(value = "/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User userAdded = userService.add(user);
        return ResponseEntity.ok().body(userAdded);
    }

}
