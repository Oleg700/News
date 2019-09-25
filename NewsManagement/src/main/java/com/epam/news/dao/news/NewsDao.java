package com.epam.news.dao.news;

import com.epam.news.model.news.News;

import java.util.List;

/**
 * Dao layer for database communication with entity {@link News}.
 *
 * @author Oleg Aliyev
 */
public interface NewsDao {

    /**
     * @return list of news.
     */
    List<News> getAll();

    /**
     * Returns news by id.
     *
     * @param id news
     * @return News
     */
    News get(final long id);

    /**
     * save news.
     *
     * @param news to save
     * @return news
     */
    News add(News news);

    /**
     * update news.
     *
     * @param news to update
     * @return news
     */
    News update(News news);

    /**
     * delete news by id.
     *
     * @param id of news to delete
     */
    void delete(long id);
}
