package com.epam.news.controller;

import com.epam.news.model.news.Comment;
import com.epam.news.model.news.CommentRequest;
import com.epam.news.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostAuthorize("hasAuthority('PRIVILEGE_WRITE_COMMENT')")
    @PostMapping(value = "/comments")
    public ResponseEntity<Comment> addComment(@RequestBody CommentRequest commentRequest) {
        Comment comment = commentService.add(commentRequest);
        return ResponseEntity.ok().body(comment);
    }
}
