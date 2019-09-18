package com.epam.news.controller;

import com.epam.news.model.news.Comment;
import com.epam.news.model.news.CommentRequest;
import com.epam.news.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Collection;

@CrossOrigin("*")
@RestController
@EnableWebMvc
@RequestMapping(value = "/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PreAuthorize("hasAuthority('PRIVILEGE_WRITE_COMMENT')")
    @PostMapping(value = "/comments/{username}")
    public ResponseEntity<Comment> addComment(@PathVariable String username, @RequestBody Comment comment) {
        Comment savedComment = commentService.add(comment, username);
        return ResponseEntity.ok().body(savedComment);
    }

    @GetMapping(value = "/news/{id}/comments/{page}")
    public ResponseEntity<Collection<Comment>> getCommentsByNewsId(@PathVariable("id") long id, @PathVariable("page") int page ) {
        Collection<Comment>  commentCollection= commentService.getCommentsByNewsId(id, page);
        return ResponseEntity.ok().body(commentCollection);
    }
}
