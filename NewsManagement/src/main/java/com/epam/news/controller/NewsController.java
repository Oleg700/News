package com.epam.news.controller;

import com.epam.news.model.news.News;
import com.epam.news.service.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

/**
 * REST API controller for processing queries with entity {@link News}.
 *
 * @author Oleg Aliyev
 */
@CrossOrigin("*")
@RestController
@EnableWebMvc
@RequestMapping(value = "/api")
public class NewsController {

    /**
     * service is used to interact with database.
     */
    @Autowired
    private NewsService newsService;

    /**
     * returns list of news in json format.
     *
     * @return list of news
     */
    @GetMapping(value = "/news")
    public ResponseEntity<List<News>> getAllNews() {
        List<News> newsList = newsService.getAll();
        return ResponseEntity.ok().body(newsList);
    }

    /**
     * get news by id
     * responseEntity is allowed for all users.
     *
     * @param id to get news by id
     * @return news
     */
    @GetMapping(value = "/news/{id}")
    public ResponseEntity<News> getNewsById(@PathVariable("id") final long id) {
        News news = newsService.get(id);
        return ResponseEntity.ok().body(news);
    }

    /**
     * To increase the interaction with database,
     * we retrieve only by 2 comment, instead of the whole list of comment.
     *
     * @param id   to get news by id
     * @param page get current comment page
     * @return news
     */
    @GetMapping(value = "/news/{id}/comment/{page}")
    public ResponseEntity<News> getNewsWithTwoRecentComments(
            @PathVariable("id") final long id,
            @PathVariable("page") final int page) {
        News news = newsService.getNewsWithTwoRecentComments(id, page);
        return ResponseEntity.ok().body(news);
    }

    /**
     * Returns added news as a result of request,
     * only users with role editor are allowed to
     * save news.
     *
     * @param news to save
     * @return news
     */
    @PreAuthorize("hasAuthority('PRIVILEGE_WRITE_NEWS')")
    @PostMapping(value = "/news")
    public ResponseEntity<News> addNews(@RequestBody final News news) {
        News newsAdded = newsService.add(news);
        return ResponseEntity.ok().body(newsAdded);
    }

    /**
     * request for updating news.
     *
     * @param id   newsId
     * @param news to update
     * @return ResponseEntity<News>
     */
    @PreAuthorize("hasAuthority('PRIVILEGE_UPDATE_NEWS')")
    @PutMapping(value = "/news/{id}")
    public ResponseEntity<News> updateNews(
            @PathVariable("id") final long id, @RequestBody final News news) {
        news.setId(id);
        News newsUpdated = newsService.update(news);
        return ResponseEntity.ok().body(newsUpdated);
    }

    /**
     * request for deleting news.
     *
     * @param id newsId
     * @return result of request
     */
    @PreAuthorize("hasAuthority('PRIVILEGE_DELETE_NEWS')")
    @DeleteMapping("/news/{id}")
    public ResponseEntity<String> deleteNews(
            @PathVariable("id") final long id) {
        newsService.delete(id);
        return ResponseEntity.ok()
                .body("News has been deleted successfully with id " + id);
    }
}
