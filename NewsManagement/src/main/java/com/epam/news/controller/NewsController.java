package com.epam.news.controller;

import com.epam.news.model.news.Comment;
import com.epam.news.model.news.CommentRequest;
import com.epam.news.model.news.News;
import com.epam.news.model.user.Privilege;
import com.epam.news.model.user.Role;
import com.epam.news.model.user.User;
import com.epam.news.service.comment.CommentService;
import com.epam.news.service.news.NewsService;
import com.epam.news.service.privilege.PrivilegeService;
import com.epam.news.service.role.RoleService;
import com.epam.news.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Collection;
import java.util.List;

@CrossOrigin("*")
@RestController
@EnableWebMvc
@RequestMapping(value = "/api")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private PrivilegeService privilegeService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;


    @PostAuthorize("isAuthenticated()")
    @PostMapping(value = "/get-role")
    public ResponseEntity<Collection<Role>> getRoleByUsername(@RequestBody User user) {
        Collection<Role> roles = userService.getRoleByUsername(user.getUsername());
        return ResponseEntity.ok().body(roles);
    }

    @GetMapping(value = "/news")
    public ResponseEntity<List<News>> getAllNews() {
        List<News> newsList = newsService.getAll();
        return ResponseEntity.ok().body(newsList);
    }

    @GetMapping(value = "/news/{id}")
    public ResponseEntity<News> getNewsById(@PathVariable("id") long id) {
        News news = newsService.get(id);
        return ResponseEntity.ok().body(news);
    }

    @PreAuthorize("hasRole('ROLE_EDITOR')")
    @PostAuthorize("hasAuthority('PRIVILEGE_WRITE')")
    @PostMapping(value = "/news")
    public ResponseEntity<News> addNews(@RequestBody News news) {
        News newsAdded = newsService.add(news);
        return ResponseEntity.ok().body(newsAdded);
    }

    @PreAuthorize("hasRole('ROLE_EDITOR')")
    @PostAuthorize("hasAuthority('PRIVILEGE_UPDATE')")
    @PutMapping(value = "/news/{id}")
    public ResponseEntity<News> updateNews(@PathVariable("id") long id, @RequestBody News news) {
        news.setId(id);
        News newsUpdated = newsService.update(news);
        return ResponseEntity.ok().body(newsUpdated);
    }

    @PreAuthorize("hasRole('ROLE_EDITOR')")
    @PostAuthorize("hasAuthority('PRIVILEGE_DELETE')")
    @DeleteMapping("/news/{id}")
    public ResponseEntity<String> deleteNews(@PathVariable("id") long id) {
        newsService.delete(id);
        return ResponseEntity.ok().body("News has been deleted successfully with id " + id);
    }

    //USERS//
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostAuthorize("hasAuthority('PRIVILEGE_READ')")
    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> newsList = userService.getAll();
        return ResponseEntity.ok().body(newsList);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostAuthorize("hasAuthority('PRIVILEGE_WRITE')")
    @PostMapping(value = "/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User userAdded = userService.add(user);
        return ResponseEntity.ok().body(userAdded);
    }


    //ROLES//
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostAuthorize("hasAuthority('PRIVILEGE_READ')")
    @GetMapping(value = "/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> listRoles = (List<Role>) roleService.getAll();
        return ResponseEntity.ok().body(listRoles);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostAuthorize("hasAuthority('PRIVILEGE_WRITE')")
    @PostMapping(value = "/roles")
    public ResponseEntity<Role> addRole(@RequestBody Role role) {
        Role role1 = roleService.add(role);
        return ResponseEntity.ok().body(role1);
    }

    //PRIVILEGES
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostAuthorize("hasAuthority('PRIVILEGE_READ')")
    @GetMapping(value = "/privileges")
    public ResponseEntity<List<Privilege>> getAllPrivileges() {
        List<Privilege> listPrivileges = (List<Privilege>) privilegeService.getAll();
        return ResponseEntity.ok().body(listPrivileges);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostAuthorize("hasAuthority('PRIVILEGE_WRITE')")
    @PostMapping(value = "/privileges")
    public ResponseEntity<Privilege> addPrivilege(@RequestBody Privilege privilege) {
        Privilege privilege1 = privilegeService.add(privilege);
        return ResponseEntity.ok().body(privilege1);
    }

    //COMMENT
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_USER')")
    @PostAuthorize("hasAuthority('PRIVILEGE_WRITE')")
    @PostMapping(value = "/comments")
    public ResponseEntity<Comment> addPrivilege(@RequestBody CommentRequest commentRequest) {
        Comment comment = commentService.add(commentRequest);
        return ResponseEntity.ok().body(comment);
    }


}
