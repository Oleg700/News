package com.epam.news.spring.controller;

import com.epam.news.model.News;
import com.epam.news.model.Privilege;
import com.epam.news.model.Role;
import com.epam.news.model.User;
import com.epam.news.service.news.NewsService;
import com.epam.news.service.privilege.PrivilegeService;
import com.epam.news.service.role.RoleService;
import com.epam.news.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@CrossOrigin("*")
@RestController
@EnableWebMvc
@RequestMapping(value = "/api")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    PrivilegeService privilegeService;

    @Autowired
    RoleService roleService;

    @Autowired
    private UserService userService;

   /* @PostAuthorize("hasAuthority('READ_PRIVILEGE')")*/
    @GetMapping(value = "/news")
    public ResponseEntity<List<News>> getAll() {
        List<News> newsList = newsService.getAll();
        return ResponseEntity.ok().body(newsList);
    }



    @GetMapping(value = "/news1")
    public ResponseEntity<User> getByName() {
        User user = userService.getByName("john");
        System.out.println("user ="+user);
        return ResponseEntity.ok().body(user);
    }


    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> newsList = userService.getAll();
        return ResponseEntity.ok().body(newsList);
    }

    @PostMapping(value = "/privileges")
    public ResponseEntity<Privilege> addPrivilege(@RequestBody Privilege privilege) {
        Privilege privilege1 = privilegeService.add(privilege);
        return ResponseEntity.ok().body(privilege1);
    }


    @PostMapping(value = "/roles")
    public ResponseEntity<Role> addRole(@RequestBody Role role) {
        Role role1 = roleService.add(role);
        return ResponseEntity.ok().body(role1);
    }



    @PostMapping(value = "/register")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User userAdded = userService.add(user);
        return ResponseEntity.ok().body(userAdded);
    }


    @GetMapping(value = "/news/{id}")
    public ResponseEntity<News> get(@PathVariable("id") long id) {
        News news = newsService.get(id);
        return ResponseEntity.ok().body(news);
    }


    @PostMapping(value = "admin/news")
    public ResponseEntity<News> add(@RequestBody News news) {
        News newsAdded = newsService.add(news);
        return ResponseEntity.ok().body(newsAdded);
    }

    @PutMapping(value = "admin/news/{id}")
    public ResponseEntity<News> update(@PathVariable("id") long id, @RequestBody News news) {
        news.setId(id);
        News newsUpdated = newsService.update(news);
        return ResponseEntity.ok().body(newsUpdated);
    }

    @DeleteMapping("admin/news/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
        newsService.delete(id);
        return ResponseEntity.ok().body("News has been deleted successfully with id " + id);
    }

    public NewsService getNewsService() {
        return newsService;
    }

    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }
}
