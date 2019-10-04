package com.epam.news.controller;

import com.epam.news.model.user.Role;
import com.epam.news.model.user.User;
import com.epam.news.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * REST API controller for processing queries with entity {@link User}.
 *
 * @author Oleg Aliyev
 */
@CrossOrigin("*")
@RestController
@EnableWebMvc
@RequestMapping(value = "/api")
public class UserController {

    /**
     * service is used to interact with database.
     */
    @Autowired
    private UserService userService;

    /**
     * returns user's roles by username on authorization.
     *
     * @param username to get roles by username
     * @return list of roles
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/users/{username}/roles")
    public ResponseEntity<Collection<Role>> getRoleByUsername(
            @PathVariable("username") final String username) {
        Collection<Role> roles = userService.getRoleByUsername(username);
        return ResponseEntity.ok().body(roles);
    }

    /**
     * returns list of users.
     *
     * @return list of news
     */
    @PreAuthorize("hasAuthority('PRIVILEGE_READ_USER')")
    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> newsList = userService.getAll();
        return ResponseEntity.ok().body(newsList);
    }

    /**
     * request to save user to database.
     *
     * @param user user to save
     * @return user
     */
    @PreAuthorize("hasAuthority('PRIVILEGE_WRITE_USER')")
    @PostMapping(value = "/users")
    public ResponseEntity<User> addUser(@RequestBody @Valid final User user) {
        User userAdded = userService.add(user);
        return ResponseEntity.ok().body(userAdded);
    }

}
