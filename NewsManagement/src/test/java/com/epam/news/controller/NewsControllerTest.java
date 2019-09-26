package com.epam.news.controller;

import com.epam.news.config.AppConfig;
import com.epam.news.model.news.News;
import com.epam.news.service.news.NewsService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class})
class NewsControllerTest {

    private static final Logger LOGGER = Logger.getLogger(NewsControllerTest.class.getName());
    private static final String NEWS_TITLE = "title_integration_test";
    private static final String NEWS_BRIEF = "brief_test";
    private static final String NEWS_CONTENT = "content_test";
    private static final int NEWS_ID = 11;
    private static final int NEWS_ID_DELETE = 13;

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
    void getAllNewsWithPort() throws Exception {
        MvcResult result = this.mockMvc
                .perform(get("http://localhost:8899/api/news")).andReturn();
        String newsString = result.getResponse().getContentAsString();
        ArrayList listNewsResult = objectMapper.readValue(newsString, new TypeReference<List<News>>(){});
        List<News> listNews = newsService.getAll();

        assertThat(listNews, containsInAnyOrder(listNewsResult.toArray()));
    }




    @Test
    void getAllNews() {
        ResponseEntity<News> responce =
                restTemplate.getForEntity("http://localhost:8899/api/news" , News.class);
            if(responce.getStatusCode() == HttpStatus.OK){
                LOGGER.info("newsList: "+responce.getStatusCode());
            }
        LOGGER.info("error");
    }

    @Test
    void getNewsWithTwoRecentComments(){
        News news = newsService.getNewsWithTwoRecentComments(11, 8);
        System.out.println(news.getComments());
        assertNotNull(news);
    }

    @Test
    void getNewsById() {
        ResponseEntity<News> response =
                restTemplate.getForEntity("http://localhost:8899/api/news/13",   News.class);
        if(response.getStatusCode() == HttpStatus.OK){
            News newsResult = response.getBody();
            assertNotNull(newsResult);
        }else{
            LOGGER.info("error " + response.getStatusCode());
        }
    }


    public static String transformToJSON(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @Test
    void addNews() throws Exception {
        News news = new News(NEWS_TITLE, NEWS_BRIEF, NEWS_CONTENT);

        MvcResult result = this.mockMvc
                .perform(post("http://localhost:8899/api/news")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(transformToJSON(news))
                      /*.header(HttpHeaders.AUTHORIZATION,
                                "Basic " + Base64Utils.encodeToString("editor123:editor".getBytes()))*/


                )
                .andReturn();
     /*   System.out.println(result.getResponse().getContentAsString());*/
     /*   String username = "admin";
        String password = "admin";
        String url = "http://localhost:8899/api/news";
        HttpHeaders headers = new HttpHeaders();
        *//*headers.setContentType(MediaType.ALL);*//*
        headers.setBasicAuth(username, password);*/

     /*   HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<News> response = restTemplate.exchange(url, HttpMethod.POST, request, News.class);

        if(response.getStatusCode() == HttpStatus.OK){
            News newsResult = response.getBody();
            assertNotNull(newsResult);
        }else {
            LOGGER.info("error " + response.getStatusCode());
        }*/

        /*ResponseEntity<News> response =
                restTemplate


                        .postForEntity("http://localhost:8899/api/news", news, News.class);
        if(response.getStatusCode() == HttpStatus.OK){
            News newsResult = response.getBody();
            assertNotNull(newsResult);
        }else{
            LOGGER.info("error " + response.getStatusCode());
        }*/



       /* News newsResult = newsService.add(news);
        assertNotNull(newsResult);*/
    }

    @Test
    void updateNews() {
        News news = newsService.get(NEWS_ID);
        news.setTitle("Longon");
        News newsResult = newsService.update(news);
        assertNotNull(newsResult);
    }

    @Test
    void deleteNews() {
        newsService.delete(NEWS_ID_DELETE);
        News news = newsService.get(NEWS_ID_DELETE);
        assertNull(news);
    }
}