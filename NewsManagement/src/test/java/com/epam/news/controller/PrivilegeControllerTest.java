package com.epam.news.controller;

import com.epam.news.config.AppConfig;
import com.epam.news.model.user.Privilege;
import com.epam.news.service.privilege.PrivilegeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
class PrivilegeControllerTest {

    @Autowired
    private PrivilegeService privilegeService;

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
    void getAllPrivileges() {

  /*      MvcResult result = this.mockMvc
                .perform(get("http://localhost:8899/api/news")).andReturn();
        String newsString = result.getResponse().getContentAsString();
        ArrayList listNewsResult = objectMapper.readValue(newsString, new TypeReference<List<News>>(){});
        List<News> listNews = newsService.getAll();

        assertThat(listNews, containsInAnyOrder(listNewsResult.toArray()));
        */
       /* List<Privilege> listPrivileges = (List<Privilege>) privilegeService.getAll();
        assertFalse(listPrivileges.isEmpty());*/
    }

    @Test
    void addPrivilege() {
        Privilege privilege = new Privilege("PRIVILEGE_TEST");
        Privilege privilegeResult = privilegeService.add(privilege);
        assertNotNull(privilegeResult);
    }
}