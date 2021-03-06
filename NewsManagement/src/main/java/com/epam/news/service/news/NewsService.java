package com.epam.news.service.news;

import
        com.epam.news.model.news.News;

import java.util.List;

/**
 * Service for wrapping {@link com.epam.news.dao.news.NewsDao}.
 *
 * <p>
 * Implementation {@link NewsServiceImpl}.
 * <p>
 *
 * @author Oleg Aliyev
 */

public interface NewsService {

    /**
     * @return list of news.
     */
    List<News> findAll();
    /*
     *//**
     * Returns news by id.
     *
     * @param id news
     * @return News
     */
    News getById(long id);

    /**
     * Returns news with 2 comments.
     *
     * @param id   news
     * @param page of current comment page on frontend side
     * @return news
     */
    News getNewsWithTwoRecentComments(long id, int page);

    /**
     * save news.
     *
     * @param news to save
     * @return news
     */
    News save(News news);

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
    Long deleteByid(long id);
}
