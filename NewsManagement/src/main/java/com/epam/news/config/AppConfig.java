package com.epam.news.config;

import com.epam.news.dao.comment.CommentDao;
import com.epam.news.dao.role.RoleDao;
import com.epam.news.dao.user.UserDao;
import com.epam.news.dao.user.UserDaoImpl;
import com.epam.news.service.news.NewsService;
import com.epam.news.service.privilege.PrivilegeService;
import com.epam.news.service.user.UserService;
import com.epam.news.service.user.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;

import javax.validation.Validator;

/**
 * Class for creating Spring beans with JavaConfig.
 *
 * <p>
 * We don't create {@link org.springframework.context.ApplicationContext},
 * Spring do to throw interface, see {@link }
 * <p>
 *
 * @author Oleg Aliyev
 */
@Configuration
@PropertySource(value= {"classpath:application.properties"})
@EnableTransactionManagement
@ComponentScans(value = {
        @ComponentScan("com.epam.news")
})
public class AppConfig {

    @Autowired
    Environment environment;

    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));

        // schema init
        Resource initSchema = new ClassPathResource("authorization/delete-authorization-data.sql");
        Resource initData = new ClassPathResource("roles-initialization/create-roles.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema, initData);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);

        return dataSource;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource =
                new ReloadableResourceBundleMessageSource();
        messageSource.addBasenames("classpath:SecurityExceptions");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }



/*    @Bean
    public ResponseEntityExceptionHandler responseEntityExceptionHandler() {
        return new RestResponseEntityExceptionHandler();
    }*/

    @Bean
    public Validator localValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }

    /**
     * Returns the RestTemplate for Integration tests.
     *
     * @return restTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * Get passwordEncoder, used to encrypt password
     * for saving to database and user authentication.
     *
     * @return passwordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Get NewsDao, which will be used in service layer {@link NewsService}.
     *
     * @return newsDaoImpl
     */
  /*  @Bean
    public NewsDao newsDao() {
        return new NewsDaoImpl();
    }

    *//**
     * Get NewsService for processing
     * with Entity {@link com.epam.news.model.news.News}.
     *
     * @return newsService
     *//*
    @Bean
    public NewsService newsService() {
        return new NewsServiceImpl(newsDao(), commentDao());
    }

    *//**
     * Get UserDao, which will be used in service layer  {@link UserService}.
     *
     * @return userDao
     */
    @Bean
    public UserDao userDao() {
        return new UserDaoImpl();
    }

    /**
     * Get UserService for processing
     * with Entity {@link com.epam.news.model.user.User}.
     *
     * @return userService
     */
    @Bean
    public UserService userService() {
        return new UserServiceImpl(userDao());
    }

    /**
     * Get RoleDao, which will be used in service layer  {@link RoleDao}.
     *
     * @return roleDao
     *//*
    @Bean
    public RoleDao roleDao() {
        return new RoleDaoImpl();
    }

    *//**
     * Get RoleService for
     * processing with Entity {@link com.epam.news.model.user.Role}.
     *
     * @return roleService
     *//*
    @Bean
    public RoleService roleService() {
        return new RoleServiceImpl(roleDao());
    }

    *//**
     * Get RoleDao,
     * which will be used in service layer  {@link PrivilegeService}.
     *
     * @return privilegeDao
     *//*
    @Bean
    public PrivilegeDao privilegeDao() {
        return new PrivilegeDaoImpl();
    }

    *//**
     * Get PrivilegeService for
     * processing with Entity {@link com.epam.news.model.user.Privilege}.
     *
     * @return privilegeService
     *//*
    @Bean
    public PrivilegeService privilegeService() {
        return new PrivilegeServiceImpl(privilegeDao());
    }

    *//**
     * Get CommentDao, which will be used in service layer  {@link CommentDao}.
     *
     * @return commentDao
     *//*
    @Bean
    public CommentDao commentDao() {
        return new CommentDaoImpl();
    }

    *//**
     * Get CommentService for
     * processing with Entity {@link com.epam.news.model.news.Comment}.
     *
     * @return commentService
     *//*
    @Bean
    public CommentService commentService() {
        return new CommentServiceImpl(commentDao(), userService());
    }*/

    /**
     * is used to get objects from MvcResult in integrations tests.
     *
     * @return object mapper
     */
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
