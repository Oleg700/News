package com.epam.news.service.comment;

import com.epam.news.model.news.Comment;
import com.epam.news.model.news.CommentRequest;

import java.util.Collection;

public interface CommentService {

    Comment add(Comment comment, String username);

    Collection<Comment> getCommentsByNewsId(long id, int page);
}
