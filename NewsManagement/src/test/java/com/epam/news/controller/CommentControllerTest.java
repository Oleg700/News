package com.epam.news.controller;

import com.epam.news.config.AppConfig;
import com.epam.news.model.news.Comment;
import com.epam.news.model.news.News;
import com.epam.news.model.user.User;
import com.epam.news.service.comment.CommentService;
import com.epam.news.service.news.NewsService;
import com.epam.news.service.user.UserService;
import com.epam.news.util.DatabaseInitialization;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
class CommentControllerTest {

    private final static long NEWS_ID = 1;
    private final static String USER_NAME = "admin";
    private final static String COMMENT_CONTENT = "admin";

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @Autowired
    NewsService newsService;

    @Autowired
    DatabaseInitialization databaseInitialization;


    @Test
    void addComment() {
        News news = newsService.get(NEWS_ID);
        User user = userService.getByName(USER_NAME);
        Comment comment = new Comment(COMMENT_CONTENT, news, user);
        Comment commentResult = commentService.add(comment, user.getUsername());
        assertNotNull(commentResult);
    }


    @Test
    void addPrivileges(){
        databaseInitialization.createPrivileges();
    }

    @Test
    void addRoles(){
        databaseInitialization.addRoles();
    }

    @Test
    void printPrivileges(){
        databaseInitialization.printPrivileges();
    }

    @Test
    void deleteFromPrivileges(){
        databaseInitialization.deleteFromPrivileges();
    }

}