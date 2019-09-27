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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Base64Utils;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
class CommentControllerTest {

    private final static long NEWS_ID = 1;
    private final static String USER_NAME = "admin";
    private final static String COMMENT_CONTENT = "admin";

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
    void addComment() throws Exception {

        //given
        News news = newsService.get(NEWS_ID);
        User user = userService.getByName(USER_NAME);
        Comment comment = new Comment(COMMENT_CONTENT, news, user);

        //when
        MvcResult result = this.mockMvc
                .perform(post("http://localhost:8899/api/news/" + NEWS_ID + "/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonConvertUtil.transformToJSON(comment))
                        .header(HttpHeaders.AUTHORIZATION,
                                "Basic " + Base64Utils.encodeToString("editor:editor".getBytes())))
                .andReturn();
        String commentString = result.getResponse().getContentAsString();
        Comment commentResult = objectMapper.readValue(commentString, Comment.class);

        //then
        assertThat(null, not(commentResult));
    }
}