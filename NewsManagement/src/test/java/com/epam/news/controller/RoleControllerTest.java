package com.epam.news.controller;

import com.epam.news.config.AppConfig;
import com.epam.news.model.user.Role;
import com.epam.news.service.role.RoleService;
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
class RoleControllerTest {

    @Autowired
    private RoleService roleService;

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
    void whenGetUriThenReturnAllRoles() throws Exception {

        //given
        List<Role> listRoles = new ArrayList<>();
        listRoles.add(new Role(1, "ROLE_ADMIN"));

        //when
        MvcResult result = this.mockMvc
                .perform(get("http://localhost:8899/api/roles")
                        .header(HttpHeaders.AUTHORIZATION,
                                "Basic " + Base64Utils.encodeToString("admin:admin".getBytes())))
                .andReturn();

        //then
        String rolesString = result.getResponse().getContentAsString();
        ArrayList listRoleResult = objectMapper
                .readValue(rolesString, new TypeReference<List<Role>>() {
                });
        assertThat(listRoles, equalTo(listRoleResult));
    }

    @Test
    void whenPostUriThenReturnAddedRole() throws Exception {

        //given
        Role role = new Role("ROLE_TEST");

        //when
        MvcResult result = this.mockMvc
                .perform(post("http://localhost:8899/api/roles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonConvertUtil.transformToJSON(role))
                        .header(HttpHeaders.AUTHORIZATION,
                                "Basic " + Base64Utils.encodeToString("admin:admin".getBytes())))
                .andReturn();

        //then
        String roleString = result.getResponse().getContentAsString();
        Role roleResult = objectMapper.readValue(roleString, Role.class);

        assertThat(roleResult, is(not(nullValue())));
        assertThat(roleResult.getName(), equalTo(role.getName()));
    }

    @Test
    void whenPostUriInvalidNameThenReturnValidationError() throws Exception {

        //given
        Role role = new Role("ROLE_TEST_MORE_THAN_20_CHAR");

        //when
        ResultActions result = this.mockMvc
                .perform(post("http://localhost:8899/api/roles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonConvertUtil.transformToJSON(role))
                        .header(HttpHeaders.AUTHORIZATION,
                                "Basic " + Base64Utils.encodeToString("admin:admin".getBytes())))
                //then
                .andExpect(status().is(HttpServletResponse.SC_BAD_REQUEST));
    }
}
