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
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScans(value = {
        @ComponentScan("com.epam.news")
})
public class AppConfig {

    @Autowired
    private Environment environment;

    @Bean
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource());
        factory.setPackagesToScan("com.epam.news");
        factory.setJpaVendorAdapter(jpaVendorAdapter());
        return factory;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getProperty("db.driver"));
        dataSource.setUrl(environment.getProperty("db.url"));
        dataSource.setUsername(environment.getProperty("db.user"));
        dataSource.setPassword(environment.getProperty("db.password"));
        return dataSource;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        ((HibernateJpaVendorAdapter) jpaVendorAdapter).setShowSql(true);
        ((HibernateJpaVendorAdapter) jpaVendorAdapter).setGenerateDdl(false);
        return jpaVendorAdapter;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(localContainerEntityManagerFactoryBean().getObject());
        return jpaTransactionManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Bean
    public NewsDao newsDao() {
        return new NewsDaoImpl();
    }

    @Bean
    public NewsService newsService() {
        return new NewsServiceImpl(newsDao(),commentDao());
    }

    @Bean
    public UserDao userDao() {
        return new UserDaoImpl();
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl(userDao());
    }


    @Bean
    public RoleDao roleDao() {
        return new RoleDaoImpl();
    }

    @Bean
    public RoleService roleService() {
        return new RoleServiceImpl(roleDao());
    }


    @Bean
    public PrivilegeDao privilegeDao() {
        return new PrivilegeDaoImpl();
    }

    @Bean
    public PrivilegeService privilegeService() {
        return new PrivilegeServiceImpl(privilegeDao());
    }

    @Bean
    public CommentDao commentDao() {
        return new CommentDaoImpl();
    }

    @Bean
    public CommentService commentService() {
        return new CommentServiceImpl(commentDao());
    }


}
