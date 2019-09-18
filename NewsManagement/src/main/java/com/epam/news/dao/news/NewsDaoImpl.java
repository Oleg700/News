package com.epam.news.dao.news;

import com.epam.news.model.news.News;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


public class NewsDaoImpl implements NewsDao {

    @PersistenceContext
    private EntityManager entityManager;

    public NewsDaoImpl() {
    }

    @Override
    public List<News> getAll() {
        return entityManager.createNamedQuery("getAllNews").getResultList();
        /*return entityManager.createNamedQuery("getAllNews", News.class).getResultList();*/
    }

    @Override
    public News get(long id) {
        return entityManager.find(News.class, id);
    }


    @Override
    public News getNewsWithTwoRecentComments(long id) {
        return entityManager.createNamedQuery("getNewsWithTwoRecentComments", News.class).getSingleResult();
    }

    @Override
    public News add(News news) {
        return entityManager.merge(news);
    }

    @Override
    public News update(News news) {
        return entityManager.merge(news);
    }

    @Override
    public void delete(long id) {
        News news = entityManager.find(News.class, id);
        entityManager.remove(news);
    }
}
