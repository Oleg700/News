package com.epam.news.service.news;

import com.epam.news.dao.comment.CommentDao;
import com.epam.news.dao.news.NewsDao;
import com.epam.news.model.news.Comment;
import com.epam.news.model.news.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * implementation of interface {@link NewsService}.
 *
 * @author Oleg Aliyev
 */
public class NewsServiceImpl implements NewsService {

    /**
     * newsDao is used to process news objects in database.
     */
    private NewsDao newsDao;

    /**
     * commentDao is used to process comment objects in database.
     */
    private CommentDao commentDao;

    /**
     * constructor.
     *
     * @param newsDao    is used to process news objects in database.
     * @param commentDao is used to process comment objects in database.
     */
    @Autowired
    public NewsServiceImpl(final NewsDao newsDao, final CommentDao commentDao) {
        this.newsDao = newsDao;
        this.commentDao = commentDao;
    }


    @Override
    public List<News> getAll() {
        return newsDao.getAll();
    }

    @Override
    public News get(final long id) {
        return (News) newsDao.get(id);
    }

    @Override
    @Transactional
    public News getNewsWithTwoRecentComments(final long id, final int page) {
        News news = newsDao.get(id);
        Collection<Comment> comments = commentDao.getCommentsByNewsId(id, page);
        news.setComments(comments);
        return news;
    }


    @Override
    @Transactional
    public News add(final News news) {
        return newsDao.add(news);
    }

    @Override
    @Transactional
    public News update(final News news) {
        return newsDao.update(news);
    }

    @Override
    @Transactional
    public void delete(final long id) {
        newsDao.delete(id);
    }
}
