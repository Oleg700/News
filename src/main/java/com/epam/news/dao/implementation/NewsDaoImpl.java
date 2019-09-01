package com.epam.news.dao.implementation;

import com.epam.news.dao.NewsDao;
import com.epam.news.model.News;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class NewsDaoImpl implements NewsDao<News> {

    SessionFactory sessionFactory;

    public NewsDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<News> getAll() {
        Session session = sessionFactory.openSession();
        List<News> newsList = session.createQuery("select new News(n.title, n.date, n.brief )\n" +
                "from News n").list();
        session.close();
        return newsList;
    }

    public News get(int id) {
        return (News) sessionFactory.openSession().get(News.class, id);
    }


    public int add(News news) {
        int id = (int) sessionFactory.openSession().save(news);
        return id;
    }

    public void update( News news) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(news);
        session.flush();
        transaction.commit();
        session.close();
    }

    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        News news = (News) session.get(News.class, id);
        session.delete(news);
        transaction.commit();
        session.close();
    }
}
