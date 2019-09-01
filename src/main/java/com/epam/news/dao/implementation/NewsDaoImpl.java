package com.epam.news.dao.implementation;

import com.epam.news.dao.NewsDao;
import com.epam.news.model.News;
import com.epam.news.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class NewsDaoImpl implements NewsDao<News> {

    /*getListOfBriefNews()*/
    public List<News> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<News> newsList = session.createQuery("select new News(n.title, n.date, n.brief )\n" +
                "from News n").list();
        session.close();
        return newsList;
    }


    public News get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        News news = (News) session.get(News.class, id);
        session.close();
        return news;
    }

    public int add(News news) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(news);
        transaction.commit();
        session.close();
        return id;
    }

    public void update(int id, News news) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        News newsToUpdate = (News) session.get(News.class, id);
        newsToUpdate.setTitle(news.getTitle());
        newsToUpdate.setDate(news.getDate());
        newsToUpdate.setBrief(news.getBrief());
        newsToUpdate.setContent(news.getContent());
        session.flush();
        transaction.commit();
        session.close();
    }

    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        News news = (News) session.get(News.class, id);
        session.delete(news);
        transaction.commit();
        session.close();
    }
}
