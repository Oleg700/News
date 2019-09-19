package com.epam.news.service.news;

import com.epam.news.dao.comment.CommentDao;
import com.epam.news.dao.news.NewsDao;
import com.epam.news.model.news.Comment;
import com.epam.news.model.news.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;


public class NewsServiceImpl implements NewsService {


    private NewsDao newsDao;


    private CommentDao commentDao;

    @Autowired
    public NewsServiceImpl(NewsDao newsDao, CommentDao commentDao)
    {
        this.newsDao = newsDao;
        this.commentDao = commentDao;
    }


    @Override
    public List<News> getAll() {
        return newsDao.getAll();
    }

    @Override
    public News get(long id) {
        return (News) newsDao.get(id);
    }

    @Override
    @Transactional
    public News getNewsWithTwoRecentComments(long id, int page) {
        News news = newsDao.get(id);
        Collection<Comment> comments = commentDao.getCommentsByNewsId(id, page);
        news.setComments(comments);
        return news;
    }


    @Override
    @Transactional
    public News add(News news) {
        return newsDao.add(news);
    }

    @Override
    @Transactional
    public News update(News news) {
        return newsDao.update(news);
    }

    @Override
    @Transactional
    public void delete(long id) {
        newsDao.delete(id);
    }
}
