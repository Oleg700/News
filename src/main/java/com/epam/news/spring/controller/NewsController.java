package com.epam.news.spring.controller;

import com.epam.news.model.News;
import com.epam.news.service.NewsServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@Controller
@EnableWebMvc
@RequestMapping(value = "/")
public class NewsController {

    private ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
    private NewsServiceImpl newsServiceImpl = (NewsServiceImpl) context.getBean("newsServiceImpl");

    @GetMapping(value = "/news")
    public ResponseEntity<List<News>> getAll() {
        List<News> newsList = newsServiceImpl.getAll();
        return ResponseEntity.ok().body(newsList);
    }

    @GetMapping(value = "/news/{id}")
    public ResponseEntity<News> get(@PathVariable("id") int id) {
        News news = newsServiceImpl.get(id);
        return ResponseEntity.ok().body(news);
    }

    @PostMapping(value = "/news")
    public ResponseEntity<String> add(@RequestBody News news) {
        int id = newsServiceImpl.add(news);
        return ResponseEntity.ok().body("News was saved with id: "+id);
    }

    @PutMapping(value = "/news")
    public ResponseEntity<String> update(@RequestBody News news){
        newsServiceImpl.update(news);
        return ResponseEntity.ok().body("News was successfully updated");
    }

    @DeleteMapping("/news/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        newsServiceImpl.delete(id);
        return ResponseEntity.ok().body("News has been deleted successfully.");
    }
}
