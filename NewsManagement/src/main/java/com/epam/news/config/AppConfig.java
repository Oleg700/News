
/**
 * package .config contains classes with bean instructions and web settings
 */
package com.epam.news.config;

import com.epam.news.dao.comment.CommentDao;
import com.epam.news.dao.comment.CommentDaoImpl;
import com.epam.news.dao.news.NewsDao;
import com.epam.news.dao.news.NewsDaoImpl;
import com.epam.news.dao.privilege.PrivilegeDao;
import com.epam.news.dao.privilege.PrivilegeDaoImpl;
import com.epam.news.dao.role.RoleDao;
import com.epam.news.dao.role.RoleDaoImpl;
import com.epam.news.dao.user.UserDao;
import com.epam.news.dao.user.UserDaoImpl;
import com.epam.news.service.comment.CommentService;
import com.epam.news.service.comment.CommentServiceImpl;
import com.epam.news.service.news.NewsService;
import com.epam.news.service.news.NewsServiceImpl;
import com.epam.news.service.privilege.PrivilegeService;
import com.epam.news.service.privilege.PrivilegeServiceImpl;
import com.epam.news.service.role.RoleService;
import com.epam.news.service.role.RoleServiceImpl;
import com.epam.news.service.user.UserService;
import com.epam.news.service.user.UserServiceImpl;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

/**
 * Class for creating Spring beans with JavaConfig.
 *
 * <p>
 * We don't create {@link org.springframework.context.ApplicationContext},
 * Spring do to throw interface, see {@link NewsWebAppInitializer}
 * <p>
 *
 * @author Oleg Aliyev
 */
@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScans(value = {
        @ComponentScan("com.epam.news")
})
public class AppConfig {

    /**
     * environment for reading rows from property.
     */
    @Autowired
    private Environment environment;

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
     * Returns factory used to
     * create transactionManager {@link JpaTransactionManager}.
     *
     * @return factoryBean
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean localContainerFactoryBean() {
        LocalContainerEntityManagerFactoryBean factory =
                new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource());
        factory.setPackagesToScan("com.epam.news");
        factory.setJpaVendorAdapter(jpaVendorAdapter());
        return factory;
    }

    /**
     * Returns dataSource with database settings.
     *
     * @return dataSource
     */
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getProperty("db.driver"));
        dataSource.setUrl(environment.getProperty("db.url"));
        dataSource.setUsername(environment.getProperty("db.user"));
        dataSource.setPassword(environment.getProperty("db.password"));
        return dataSource;
    }

    /**
     * Get JpaVendorAdapter with specific database settings.
     *
     * @return jpaVendorAdapter
     */
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        ((HibernateJpaVendorAdapter) jpaVendorAdapter).setShowSql(true);
        ((HibernateJpaVendorAdapter) jpaVendorAdapter).setGenerateDdl(false);
        return jpaVendorAdapter;
    }

    /**
     * Get transactionManager for creating transactions with database.
     *
     * @return transactionManager
     */
    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager jpaTransactionManager
                = new JpaTransactionManager();
        jpaTransactionManager
                .setEntityManagerFactory(localContainerFactoryBean()
                .getObject());
        return jpaTransactionManager;
    }

    /**
     * Get passwordEncoder, used to encrypt password
     * for saving to database and user authentication.
     *
     * @return passwordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    /**
     * Get NewsDao, which will be used in service layer {@link NewsService}.
     *
     * @return newsDaoImpl
     */
    @Bean
    public NewsDao newsDao() {
        return new NewsDaoImpl();
    }

    /**
     * Get NewsService for processing
     * with Entity {@link com.epam.news.model.news.News}.
     *
     * @return newsService
     */
    @Bean
    public NewsService newsService() {
        return new NewsServiceImpl(newsDao(), commentDao());
    }

    /**
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
     */
    @Bean
    public RoleDao roleDao() {
        return new RoleDaoImpl();
    }

    /**
     * Get RoleService for
     * processing with Entity {@link com.epam.news.model.user.Role}.
     *
     * @return roleService
     */
    @Bean
    public RoleService roleService() {
        return new RoleServiceImpl(roleDao());
    }

    /**
     * Get RoleDao,
     * which will be used in service layer  {@link PrivilegeService}.
     *
     * @return privilegeDao
     */
    @Bean
    public PrivilegeDao privilegeDao() {
        return new PrivilegeDaoImpl();
    }

    /**
     * Get PrivilegeService for
     * processing with Entity {@link com.epam.news.model.user.Privilege}.
     *
     * @return privilegeService
     */
    @Bean
    public PrivilegeService privilegeService() {
        return new PrivilegeServiceImpl(privilegeDao());
    }

    /**
     * Get CommentDao, which will be used in service layer  {@link CommentDao}.
     *
     * @return commentDao
     */
    @Bean
    public CommentDao commentDao() {
        return new CommentDaoImpl();
    }

    /**
     * Get CommentService for
     * processing with Entity {@link com.epam.news.model.news.Comment}.
     *
     * @return commentService
     */
    @Bean
    public CommentService commentService() {
        return new CommentServiceImpl(commentDao(), userService());
    }


}
