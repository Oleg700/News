package com.epam.news.service.news;

import com.epam.news.model.news.Comment;
import com.epam.news.model.news.News;
import com.epam.news.repository.CommentRepository;
import com.epam.news.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class NewsServiceImpl implements NewsService {

    private NewsRepository newsRepository;

    private CommentRepository commentRepository;

    @Autowired
    public NewsServiceImpl(final NewsRepository newsRepository,
                           final CommentRepository commentRepository) {
        this.newsRepository = newsRepository;
        this.commentRepository = commentRepository;
    }


    @Override
    public News getNewsWithTwoRecentComments(final long id, final int page) {
        News news = newsRepository.getOne(id);
        Pageable pageable = PageRequest.of(page, 2, Sort.by("id"));
        Page<Comment> pageComments = commentRepository.findById(id, pageable);
        Collection<Comment> comments = pageComments.getContent();
        news.setComments(comments);
        return news;
    }
}
