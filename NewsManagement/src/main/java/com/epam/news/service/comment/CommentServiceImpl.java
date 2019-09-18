package com.epam.news.service.comment;

import com.epam.news.dao.comment.CommentDao;
import com.epam.news.model.news.Comment;
import com.epam.news.model.news.CommentRequest;
import com.epam.news.model.user.User;
import com.epam.news.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.Collection;

public class CommentServiceImpl implements CommentService {


    private CommentDao commentDao;

    @Autowired
    private UserService userService;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    @Transactional
    public Comment add(Comment comment, String username) {
        User user = userService.getByName(username);
        comment.setUser(user);
        return commentDao.add(comment);
    }

    @Override
    public Collection<Comment> getCommentsByNewsId(long id, int page) {
        return commentDao.getCommentsByNewsId(id,page);
    }
}
