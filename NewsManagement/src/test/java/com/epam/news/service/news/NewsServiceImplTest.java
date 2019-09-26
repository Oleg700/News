/*
package com.epam.news.service.news;

import com.epam.news.config.AppConfig;
import com.epam.news.dao.comment.CommentDao;
import com.epam.news.dao.news.NewsDao;
import com.epam.news.model.news.Comment;
import com.epam.news.model.news.News;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
class NewsServiceImplTest {

    private final static int NEWS_ID = 11;
    private final static int PAGE = 2;

    @Test
    void getNewsWithTwoRecentComments() {

        //when
        NewsDao newsDao = mock(NewsDao.class);
        CommentDao commentDao = mock(CommentDao.class);

        News news = new News(NEWS_ID, "title_test",
                LocalDateTime.now(), "brief_test", "content_test");
        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(new Comment((long) 1, "first comment"));
        comments.add(new Comment((long) 2, "second comment"));


        when(newsDao.get(13)).thenReturn(news);
        when(commentDao.getCommentsByNewsId(NEWS_ID, PAGE))
                .thenReturn(comments);

        //then
        Collection<Comment> commentsResult = commentDao.
                getCommentsByNewsId(NEWS_ID, PAGE);
        News newsResult = newsDao.get(NEWS_ID);

        assertThat(commentsResult, equalTo(comments));
        assertThat(newsResult, equalTo(news));
    }
}*/
