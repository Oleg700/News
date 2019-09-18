package com.epam.news.dao.comment;

import com.epam.news.model.news.Comment;

import java.util.Collection;

public interface CommentDao {

    Comment add(Comment comment);

    Collection<Comment> getCommentsByNewsId(long id, int page);
}
