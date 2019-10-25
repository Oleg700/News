package com.epam.news.service.comment;

import com.epam.news.model.news.Comment;

public interface CommentService {

    Comment add(Comment comment, String username);
}


