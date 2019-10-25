package com.epam.news.service.news;

import com.epam.news.model.news.News;

public interface NewsService {

    News getNewsWithTwoRecentComments(long id, int page);

}
