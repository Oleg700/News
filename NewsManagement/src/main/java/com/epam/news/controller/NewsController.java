package com.epam.news.controller;

import com.epam.news.model.news.News;
import com.epam.news.service.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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


    @GetMapping(value = "/news")
    public ResponseEntity<List<News>> getAllNews() {
        List<News> newsList = newsService.getAll();
        return ResponseEntity.ok().body(newsList);
    }

    @GetMapping(value = "/news/{id}")
    public  ResponseEntity<News> getNewsById(@PathVariable("id") long id) {
        News news = newsService.get(id);
        return ResponseEntity.ok().body(news);
    }

    @GetMapping(value = "/news/{id}/comments/{page}")
    public ResponseEntity<News> getNewsWithTwoRecentComments(@PathVariable("id") long id,
                                                             @PathVariable("page") int page) {
        News news = newsService.getNewsWithTwoRecentComments(id, page);
        return ResponseEntity.ok().body(news);
    }

    @PreAuthorize("hasAuthority('PRIVILEGE_WRITE_NEWS')")
    @PostMapping(value = "/news")
    public ResponseEntity<News> addNews(@RequestBody News news) {
        News newsAdded = newsService.add(news);
        return ResponseEntity.ok().body(newsAdded);
    }

    @PreAuthorize("hasAuthority('PRIVILEGE_UPDATE_NEWS')")
    @PutMapping(value = "/news/{id}")
    public ResponseEntity<News> updateNews(@PathVariable("id") long id, @RequestBody News news) {
        news.setId(id);
        News newsUpdated = newsService.update(news);
        return ResponseEntity.ok().body(newsUpdated);
    }

    @PreAuthorize("hasAuthority('PRIVILEGE_DELETE_NEWS')")
    @DeleteMapping("/news/{id}")
    public ResponseEntity<String> deleteNews(@PathVariable("id") long id) {
        newsService.delete(id);
        return ResponseEntity.ok().body("News has been deleted successfully with id " + id);
    }
}
