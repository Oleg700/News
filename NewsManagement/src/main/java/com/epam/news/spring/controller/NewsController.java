package com.epam.news.spring.controller;

import com.epam.news.model.Authority;
import com.epam.news.model.News;
import com.epam.news.model.User;
import com.epam.news.service.news.NewsService;
import com.epam.news.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    private UserService userService;

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> newsList = userService.getAll();
        return ResponseEntity.ok().body(newsList);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User userAdded = userService.add(user);
        return ResponseEntity.ok().body(userAdded);
    }


    @GetMapping(value = "/authority")
    public ResponseEntity<List<Authority>> getAllAuthorities() {
        List<Authority> newsList = newsService.getAllAuthorities();
        return ResponseEntity.ok().body(newsList);
    }

    @PostMapping(value = "/authority")
    public ResponseEntity<Authority> addAuthority(@RequestBody Authority authority) {
        Authority authority1 = newsService.addAuthority(authority);
        return ResponseEntity.ok().body(authority1);
    }

    @GetMapping(value = "/news")
    public ResponseEntity<List<News>> getAll() {
        List<News> newsList = newsService.getAll();
        return ResponseEntity.ok().body(newsList);
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
