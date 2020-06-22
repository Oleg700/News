package com.epam.news.controller.news;

import com.epam.news.model.news.News;
import com.epam.news.service.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RepositoryRestController
public class NewsController {


    @Autowired
    private NewsService newsService;

    @GetMapping(value = "/news/{id}/comments/{page}")
    public ResponseEntity<News> getNewsWithTwoRecentComments(
            @PathVariable("id") final long id,
            @PathVariable("page") final int page) {
        News news = newsService.getNewsWithTwoRecentComments(id, page);
        return ResponseEntity.ok().body(news);
    }

}
