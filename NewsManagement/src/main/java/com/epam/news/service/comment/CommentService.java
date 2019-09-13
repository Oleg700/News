package com.epam.news.service.comment;

import com.epam.news.model.Comment;
import com.epam.news.model.CommentRequest;

public interface CommentService {

    Comment add(CommentRequest commentRequest);
}
