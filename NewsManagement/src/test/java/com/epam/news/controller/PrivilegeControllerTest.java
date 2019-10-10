package com.epam.news.controller;

import com.epam.news.config.AppConfig;
import com.epam.news.model.user.Privilege;
import com.epam.news.service.privilege.PrivilegeService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    @Sql(scripts
            = {"classpath:authorization/delete-authorization-tables.sql",
            "classpath:user/create-admin.sql"})
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .addFilters(this.springSecurityFilterChain)
                .build();
    }

    @Test
    void whenGetUriThenReturnAllPrivileges() throws Exception {

        //given
        List<Privilege> listPrivileges = new ArrayList<>();
        listPrivileges.add(new Privilege(1, "PRIVILEGE_WRITE_USER"));
        listPrivileges.add(new Privilege(2, "PRIVILEGE_WRITE_ROLE"));
        listPrivileges.add(new Privilege(3, "PRIVILEGE_WRITE_PRIVILEGE"));
        listPrivileges.add(new Privilege(4, "PRIVILEGE_READ_USER"));
        listPrivileges.add(new Privilege(5, "PRIVILEGE_READ_ROLE"));
        listPrivileges.add(new Privilege(6, "PRIVILEGE_READ_PRIVILEGE"));
        listPrivileges.add(new Privilege(7, "PRIVILEGE_WRITE_COMMENT"));

        //when
        MvcResult result = this.mockMvc
                .perform(get("http://localhost:8899/api/privileges")
                        .header(HttpHeaders.AUTHORIZATION,
                                "Basic " + Base64Utils.encodeToString("admin:admin".getBytes())))
                .andReturn();

        //then
        String privilegesString = result.getResponse().getContentAsString();
        System.out.println(privilegesString);
        ArrayList listPrivilegesResult = objectMapper
                .readValue(privilegesString, new TypeReference<List<Privilege>>() {
                });

        assertThat(listPrivileges, equalTo(listPrivilegesResult));
    }

    @Test
    void whenPostUriThenReturnAddedPrivilege() throws Exception {

        //given
        Privilege privilege = new Privilege("PRIVILEGE_TEST");

        //when
        MvcResult result = this.mockMvc
                .perform(post("http://localhost:8899/api/privileges")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonConvertUtil.transformToJSON(privilege))
                        .header(HttpHeaders.AUTHORIZATION,
                                "Basic " + Base64Utils.encodeToString("admin:admin".getBytes())))
                .andReturn();

        //then
        String privilegeString = result.getResponse().getContentAsString();
        Privilege privilegeResult = objectMapper
                .readValue(privilegeString, Privilege.class);
        assertThat(privilegeResult, is(not(nullValue())));
        assertThat(privilegeResult.getName(), equalTo(privilege.getName()));
    }

    @Test
    void whenPostUriInvalidNameThenReturnValidationError() throws Exception {

        //given
        Privilege privilege = new Privilege("PRIVILEGE_TEST_MORE_THAN_20_CHARS");

        //when
        ResultActions result = this.mockMvc
                .perform(post("http://localhost:8899/api/privileges")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonConvertUtil.transformToJSON(privilege))
                        .header(HttpHeaders.AUTHORIZATION,
                                "Basic " + Base64Utils.encodeToString("admin:admin".getBytes())))
                //then
                .andExpect(status().is(HttpServletResponse.SC_BAD_REQUEST));
    }
}