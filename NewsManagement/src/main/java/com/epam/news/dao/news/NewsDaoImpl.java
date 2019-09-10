package com.epam.news.dao.news;

import com.epam.news.model.News;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


public class NewsDaoImpl implements NewsDao {

    private final static String QUERY_SELECT_ALL_BRIEF_NEW = "select new News(n.id, n.title, n.date, n.brief )\n" +
            "from News n";

    @PersistenceContext
    private EntityManager entityManager;

    public NewsDaoImpl() {
    }


    public List<News> getAll() {
        List<News> newsList = entityManager.createQuery(QUERY_SELECT_ALL_BRIEF_NEW).getResultList();
        return newsList;
    }


    public News get(long id) {
        return entityManager.find(News.class, id);
    }

    @Transactional
    public News add(News news) {
        return entityManager.merge(news);
    }

    @Transactional
    public News update(News news) {
        return entityManager.merge(news);
    }

    @Transactional
    public void delete(long id) {
        News news = entityManager.find(News.class, id);
        entityManager.remove(news);
    }
}
