package com.epam.news.controller;

import com.epam.news.model.news.Comment;
import com.epam.news.model.news.CommentRequest;
import com.epam.news.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

public class CommentController {

    @Autowired
    private CommentService commentService;

    @PreAuthorize("hasAuthority('PRIVILEGE_WRITE_COMMENT')")
    @PostMapping(value = "/comments")
    public ResponseEntity<Comment> addComment(@RequestBody CommentRequest commentRequest) {
        Comment comment = commentService.add(commentRequest);
        return ResponseEntity.ok().body(comment);
    }

    @GetMapping(value = "/news/{id}/comments/{page}")
    public ResponseEntity<Collection<Comment>> getNewsById(@PathVariable("id") long id, @PathVariable("page") int page ) {
        Collection<Comment>  commentCollection= commentService.getCommentsByNewsId(id, page);
        return ResponseEntity.ok().body(commentCollection);
    }
}
