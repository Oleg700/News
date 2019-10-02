package com.epam.news.controller;

import com.epam.news.config.AppConfig;
import com.epam.news.model.news.Comment;
import com.epam.news.model.news.News;
import com.epam.news.model.user.User;
import com.epam.news.service.comment.CommentService;
import com.epam.news.service.news.NewsService;
import com.epam.news.service.user.UserService;
import com.epam.news.util.JsonConvertUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Base64Utils;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
class CommentControllerTest {



    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @Autowired
    NewsService newsService;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .addFilters(this.springSecurityFilterChain)
                .build();
    }

    @Test
    @Sql(scripts = {"classpath:comment/delete-all-comments.sql"})
    @Sql(scripts =
            {"classpath:authorization/delete-authorization-tables.sql",
                    "classpath:user/create-editor.sql"})
    void whenGetUriThenReturnAddComment() throws Exception {

        //given
        News news = new News(1, "London", "brief", "content");
        User user = new User((long) 1, "editor", "$2a$10$ImQ1cAL0JnXCMwojGnvGzOscT5adZrjEHqIIynGqfXhDRM.pN/2Ua");
        Comment comment = new Comment("admin", news, user);

        //when
        MvcResult result = this.mockMvc
                .perform(post("http://localhost:8899/api/news/" + 1 + "/comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonConvertUtil.transformToJSON(comment))
                        .header(HttpHeaders.AUTHORIZATION,
                                "Basic " + Base64Utils.encodeToString("editor:editor".getBytes())))
                .andReturn();

        //then
        String commentString = result.getResponse().getContentAsString();
        Comment commentResult = objectMapper.readValue(commentString, Comment.class);

        assertThat(commentResult, is(not(nullValue())));
        assertThat(commentResult.getContent(), equalTo(comment.getContent()));
    }
}