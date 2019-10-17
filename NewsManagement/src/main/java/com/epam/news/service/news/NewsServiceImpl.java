package com.epam.news.service.news;

import com.epam.news.dao.comment.CommentDao;
import com.epam.news.model.news.News;
import com.epam.news.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * implementation of interface {@link NewsService}.
 *
 * @author Oleg Aliyev
 */
@Service
public class NewsServiceImpl implements NewsService {

    /**
     * newsDao is used to process news objects in database.
     */
    private NewsRepository newsRepository;

    /**
     * commentDao is used to process comment objects in database.
     */
    private CommentDao commentDao;


    @Autowired
    public NewsServiceImpl(final NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }


    @Override
    public List<News> findAll() {
        return newsRepository.findAll();
    }

    @Override
    public News getById(final long id) {
        return newsRepository.getById(id);
    }

    @Override
    public News save(final News news) {
        return newsRepository.save(news);
    }
/*
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
    public News update(final News news) {
        return newsDao.update(news);
    }

    @Override
    @Transactional
    public void delete(final long id) {
        newsDao.delete(id);
    }*/
}
