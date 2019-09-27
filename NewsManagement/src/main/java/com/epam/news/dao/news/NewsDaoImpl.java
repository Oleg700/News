package com.epam.news.dao.news;

import com.epam.news.model.news.News;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Implementation of interface {@link NewsDao}.
 *
 * @author Oleg Aliyev
 */
public class NewsDaoImpl implements NewsDao {

    /**
     * entity manager is used for communication with database.
     */
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<News> getAll() {
        return entityManager.createNamedQuery("getAllNews")
                .getResultList();
    }

    @Override
    public News get(final long id) {
        return entityManager.find(News.class, id);
    }


    @Override
    public News add(final News news) {
        return entityManager.merge(news);
    }

    @Override
    public News update(final News news) {
        return entityManager.merge(news);
    }

    @Override
    public void delete(final long id) {
        News news = entityManager.find(News.class, id);
        entityManager.remove(news);
    }
}
