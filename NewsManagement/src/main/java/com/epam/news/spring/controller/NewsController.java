package com.epam.news.spring.controller;

import com.epam.news.model.News;
import com.epam.news.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@CrossOrigin("*")
@EnableWebMvc
@RequestMapping(value = "/api")
public class NewsController {

    private NewsService newsService;

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

    @PostMapping(value = "/news")
    public ResponseEntity<News> add(@RequestBody News news) {
        News newsAdded = newsService.add(news);
        return ResponseEntity.ok().body(newsAdded);
    }

    @PutMapping(value = "/news/{id}")
    public ResponseEntity<News> update(@PathVariable("id") long id, @RequestBody News news) {
        news.setId(id);
        News newsUpdated = newsService.update(news);
        return ResponseEntity.ok().body(newsUpdated);
    }

    @DeleteMapping("/news/{id}")
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
