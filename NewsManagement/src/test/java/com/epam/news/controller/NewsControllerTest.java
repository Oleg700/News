package com.epam.news.controller;

import com.epam.news.config.AppConfig;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class})
class NewsControllerTest {

    private static final Logger LOGGER = Logger.getLogger(NewsControllerTest.class.getName());
    private static final String NEWS_TITLE = "title_integration_test";
    private static final String NEWS_BRIEF = "brief_test";
    private static final String NEWS_CONTENT = "content_test";
    private static final int NEWS_ID = 1;
    private static final int NEWS_ID_DELETE = 2;

    @Autowired
    NewsService newsService;

    @Autowired
    RestTemplate restTemplate;


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
    void getAllNews() throws Exception {

        //given
        List<News> listNews = newsService.getAll();

        //when
        MvcResult result = this.mockMvc
                .perform(get("http://localhost:8899/api/news")).andReturn();
        String newsString = result.getResponse().getContentAsString();
        ArrayList listNewsResult = objectMapper.readValue(newsString, new TypeReference<List<News>>(){});

        //then
        assertThat(listNews, containsInAnyOrder(listNewsResult.toArray()));
    }

    @Test
    void getNewsWithTwoRecentComments() throws Exception {

        //given
        News news = newsService.getNewsWithTwoRecentComments(1, 1);

        //when
        MvcResult result = this.mockMvc
                .perform(get("http://localhost:8899/api/news/1/comments/1")).andReturn();
        String newsString = result.getResponse().getContentAsString();
        News newsResult = objectMapper.readValue(newsString, News.class);

        //then
        assertThat(news, equalTo(newsResult));
    }

    @Test
    void getNewsById() throws Exception {

        //given
        News news = newsService.get(1);

        //when
        MvcResult result = this.mockMvc
                .perform(get("http://localhost:8899/api/news/1")).andReturn();
        String newsString = result.getResponse().getContentAsString();
        News newsResult = objectMapper.readValue(newsString, News.class);

        //then
        assertThat(news, equalTo(newsResult));

    }

    @Test
    void addNews() throws Exception {

        //given
        News news = new News(NEWS_TITLE, NEWS_BRIEF, NEWS_CONTENT);

        //when
        MvcResult result = this.mockMvc
                .perform(post("http://localhost:8899/api/news")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonConvertUtil.transformToJSON(news))
                      .header(HttpHeaders.AUTHORIZATION,
                                "Basic " + Base64Utils.encodeToString("editor:editor".getBytes())))
                .andReturn();
        String newsString = result.getResponse().getContentAsString();
        News newsResult = objectMapper.readValue(newsString, News.class);
        News newsAdded = newsService.get(newsResult.getId());

        //then
        assertThat(newsAdded, equalTo(newsResult));
    }

    @Test
    void updateNews() throws Exception {

        //given
        News news = newsService.get(NEWS_ID);
        news.setTitle("Longon");

        //when
        MvcResult result = this.mockMvc
                .perform(put("http://localhost:8899/api/news/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonConvertUtil.transformToJSON(news))
                        .header(HttpHeaders.AUTHORIZATION,
                                "Basic " + Base64Utils.encodeToString("editor:editor".getBytes())))
                .andReturn();
        String newsString = result.getResponse().getContentAsString();
        News newsResult = objectMapper.readValue(newsString, News.class);

        //then
        assertThat(news, equalTo(newsResult));
    }

    @Test
    void deleteNews() throws Exception {

        //when
        MvcResult result = this.mockMvc
                .perform(delete("http://localhost:8899/api/news/"
                        + NEWS_ID_DELETE)
                        .header(HttpHeaders.AUTHORIZATION,
                                "Basic " + Base64Utils.encodeToString("editor:editor".getBytes())))
                .andReturn();
        News newsDeleted = newsService.get(NEWS_ID_DELETE);

        //then
        assertThat(null, equalTo(newsDeleted));
    }


}