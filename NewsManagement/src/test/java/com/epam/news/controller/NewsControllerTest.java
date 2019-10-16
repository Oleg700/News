package com.epam.news.controller;

import com.epam.news.config.AppConfig;
import com.epam.news.model.news.Comment;
import com.epam.news.model.news.News;
import com.epam.news.service.news.NewsService;
import com.epam.news.util.JsonConvertUtil;
import com.fasterxml.jackson.core.type.TypeReference;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Base64Utils;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class})
class NewsControllerTest {

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
    @Sql(scripts =
            {"classpath:authorization/delete-authorization-tables.sql",
                    "classpath:user/create-editor.sql"})
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .addFilters(this.springSecurityFilterChain)
                .build();
    }

    @Test
    @Sql(scripts =
            {"classpath:comment/delete-all-comments.sql",
                    "classpath:news/delete-all-news.sql",
                    "classpath:news/create-news.sql"})
    void whenGetUriThenReturnListOfNews() throws Exception {

        //given
        ArrayList<News> customlistNews = new ArrayList<>();
        customlistNews.add(new News(1, "London", "brief", "content"));
        customlistNews.add(new News(2, "Berlin", "brief", "content"));

        //when
        MvcResult result = this.mockMvc
                .perform(get("http://localhost:8899/api/news")).andReturn();

        //then
        String newsString = result.getResponse().getContentAsString();
        ArrayList listNewsResult = objectMapper.readValue(newsString, new TypeReference<List<News>>() {
        });

        assertThat(customlistNews, equalTo(listNewsResult));
    }

    @Test
    @Sql(scripts =
            {"classpath:comment/delete-all-comments.sql",
                    "classpath:news/delete-all-news.sql",
                    "classpath:news/create-news.sql",
                    "classpath:comment/create-comments.sql",
            })
    @Sql(scripts = "classpath:comment/delete-all-comments.sql", executionPhase = AFTER_TEST_METHOD)
    void whenGetUriThenReturnNewsWithTwoRecentComments() throws Exception {

        //given
        News news = new News(1, "London", "brief", "content");
        Comment comment = new Comment((long) 1, "content");
        List<Comment> listComments = new ArrayList();
        listComments.add(comment);
        news.setComments(listComments);

        //when
        MvcResult result = this.mockMvc
                .perform(get("http://localhost:8899/api/news/1/comment/1")).andReturn();

        //then
        String newsString = result.getResponse().getContentAsString();
        News newsResult = objectMapper.readValue(newsString, News.class);
        assertThat(news, equalTo(newsResult));
    }

    @Test
    @Sql(scripts =
            {"classpath:news/delete-all-news.sql",
                    "classpath:news/create-news.sql"})
    void whenGetUriThenReturnNewsById() throws Exception {

        //given
        News news = new News(1, "London", "brief", "content");

        //when
        MvcResult result = this.mockMvc
                .perform(get("http://localhost:8899/api/news/1")).andReturn();

        //then
        String newsString = result.getResponse().getContentAsString();
        News newsResult = objectMapper.readValue(newsString, News.class);

        assertThat(news, equalTo(newsResult));
    }

    @Test
    @Sql(scripts = {"classpath:news/delete-all-news.sql",
            "classpath:user/create-editor.sql"})
    void whenPostUriThenReturnAddedNews() throws Exception {

        //given
        News news = new News(1, "title", "brief", "content");

        //when
        MvcResult result = this.mockMvc
                .perform(post("http://localhost:8899/api/news")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonConvertUtil.transformToJSON(news))
                        .header(HttpHeaders.AUTHORIZATION,
                                "Basic " + Base64Utils.encodeToString("editor:editor".getBytes())))
                .andReturn();

        //then
        String newsString = result.getResponse().getContentAsString();
        News newsResult = objectMapper.readValue(newsString, News.class);

        assertThat(news.getTitle(), equalTo(newsResult.getTitle()));
        assertThat(news.getBrief(), equalTo(newsResult.getBrief()));
        assertThat(news.getContent(), equalTo(newsResult.getContent()));
    }


    @Test
    @Sql(scripts
            = {"classpath:news/delete-all-news.sql",
            "classpath:authorization/delete-authorization-tables.sql"})
    void whenPostNewsWithoutAuthorizationUriThenReturnErrorNotAuthorized()
            throws Exception {

        //given
        News news = new News(1, "title", "brief", "content");

        //when
        ResultActions result = this.mockMvc
                .perform(post("http://localhost:8899/api/news")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonConvertUtil.transformToJSON(news))
                        .header(HttpHeaders.AUTHORIZATION,
                                "Basic " + Base64Utils.encodeToString("anonym:anonym".getBytes())))

                //then
                //error 401
                .andExpect(status().is(HttpServletResponse.SC_UNAUTHORIZED));
    }


    @Test
    @Sql(scripts
            = {"classpath:news/delete-all-news.sql",
            "classpath:authorization/delete-authorization-tables.sql",
            "classpath:user/create-admin.sql"})
    void whenPostNewsWithoutAuthorizationUriThenReturnErrorNotAuthenticated()
            throws Exception {

        //given
        News news = new News(1, "title", "brief", "content");

        //when
        ResultActions result = this.mockMvc
                .perform(post("http://localhost:8899/api/news")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonConvertUtil.transformToJSON(news))
                        .header(HttpHeaders.AUTHORIZATION,
                                "Basic " + Base64Utils.encodeToString("admin:admin".getBytes())))

                //then
                //error 403
                .andExpect(status().is(HttpServletResponse.SC_FORBIDDEN));
    }

    @Test
    @Sql(scripts = {"classpath:news/delete-all-news.sql",
            "classpath:authorization/delete-authorization-tables.sql",
            "classpath:user/create-editor.sql"
    })
    void whenPostNewsWithoutBriefUriThenReturnValidationError() throws Exception {

        //given
        News news = new News(1, "title", "content");

        //when
        ResultActions result = this.mockMvc
                .perform(post("http://localhost:8899/api/news")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonConvertUtil.transformToJSON(news))
                        .header(HttpHeaders.AUTHORIZATION,
                                "Basic " + Base64Utils.encodeToString("editor:editor".getBytes())))
                //then
                .andExpect(status().is(HttpServletResponse.SC_BAD_REQUEST));

    }

    @Test
    @Sql(scripts
            = {"classpath:news/delete-all-news.sql",
            "classpath:news/create-news.sql"})
    void whenPutUriThenReturnUpdatedNews() throws Exception {

        //given
        News news = new News(1, "Berlin", "brief", "content");

        //when
        MvcResult result = this.mockMvc
                .perform(put("http://localhost:8899/api/news/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonConvertUtil.transformToJSON(news))
                        .header(HttpHeaders.AUTHORIZATION,
                                "Basic " + Base64Utils.encodeToString("editor:editor".getBytes())))
                .andReturn();

        //then
        String newsString = result.getResponse().getContentAsString();
        News newsResult = objectMapper.readValue(newsString, News.class);

        assertThat(news, equalTo(newsResult));
    }

    @Test
    @Sql(scripts
            = {"classpath:news/delete-all-news.sql",
            "classpath:news/create-news.sql"})
    void whenDeleteUriThenReturnDeletedNews() throws Exception {

        //when
        MvcResult result = this.mockMvc
                .perform(delete("http://localhost:8899/api/news/"
                        + 1)
                        .header(HttpHeaders.AUTHORIZATION,
                                "Basic " + Base64Utils.encodeToString("editor:editor".getBytes())))
                .andReturn();

        //then
    /*    News newsDeleted = newsService.get(1);

        assertThat(newsDeleted, is(nullValue()));*/
    }
}