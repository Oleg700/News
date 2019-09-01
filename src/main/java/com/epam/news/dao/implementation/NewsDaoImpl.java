package com.epam.news.dao.implementation;

import com.epam.news.dao.NewsDao;
import com.epam.news.model.News;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;


public class NewsDaoImpl implements NewsDao {

    private final static String QUERY_SELECT_ALL_BRIEF_NEW = "select new News(n.id, n.title, n.date, n.brief )\n" +
            "from News n";

    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    public NewsDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        createEntityManager();
    }

    private void createEntityManager(){
        entityManager = entityManagerFactory.createEntityManager();
    }

    public List<News> getAll() {
        List<News> newsList = entityManager.createQuery(QUERY_SELECT_ALL_BRIEF_NEW).getResultList();
        return newsList;

    }

    public News get(int id) {
        return entityManager.find(News.class, id);
    }

    public News add(News news) {
        entityManager.getTransaction().begin();
        News newsAdded = entityManager.merge(news);
        entityManager.getTransaction().commit();
        return newsAdded;

    }

    public News update(News news) {
        entityManager.getTransaction().begin();
        News newsAdded = entityManager.merge(news);
        entityManager.getTransaction().commit();
        return newsAdded;
    }

    public void delete(int id) {
        News news = entityManager.find(News.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(news);
        entityManager.getTransaction().commit();
    }
}
