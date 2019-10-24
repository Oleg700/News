package com.epam.news.service;

import com.epam.news.model.news.Comment;
import com.epam.news.model.user.User;
import com.epam.news.repository.CommentRepository;
import com.epam.news.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    private UserRepository userRepository;

    @Autowired
    public CommentServiceImpl(final CommentRepository commentRepository,
                              final UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Comment add(final Comment comment, final String username) {
        User user = userRepository.findDistinctFirstByUsername(username);
        comment.setUser(user);
        return commentRepository.save(comment);
    }


}
