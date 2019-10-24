package com.epam.news.service;

import com.epam.news.model.news.Comment;

public interface CommentService {

    Comment add(Comment comment, String username);
}


