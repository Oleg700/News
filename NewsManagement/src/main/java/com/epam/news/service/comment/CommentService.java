package com.epam.news.service.comment;

import com.epam.news.model.news.Comment;
import com.epam.news.model.news.CommentRequest;

public interface CommentService {

    Comment add(CommentRequest commentRequest);
}
