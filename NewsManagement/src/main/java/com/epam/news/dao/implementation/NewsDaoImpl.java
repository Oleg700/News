package com.epam.news.dao.implementation;

import com.epam.news.dao.NewsDao;
import com.epam.news.model.News;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
public class NewsDaoImpl implements NewsDao {

    private final static String QUERY_SELECT_ALL_BRIEF_NEW = "select new News(n.id, n.title, n.date, n.brief )\n" +
            "from News n";

    private EntityManager entityManager;

    public NewsDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<News> getAll() {
        List<News> newsList = entityManager.createQuery(QUERY_SELECT_ALL_BRIEF_NEW).getResultList();
        return newsList;
    }


    public News get(long id) {
        return entityManager.find(News.class, id);
    }


    public News add(News news) {
        return entityManager.merge(news);

    }


    public News update(News news) {
        return entityManager.merge(news);
    }


    public void delete(long id) {
        News news = entityManager.find(News.class, id);
        entityManager.remove(news);
    }
}
