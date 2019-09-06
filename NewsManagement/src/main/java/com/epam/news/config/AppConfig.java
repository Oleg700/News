package com.epam.news.config;

import com.epam.news.dao.news.NewsDao;
import com.epam.news.dao.news.NewsDaoImpl;
import com.epam.news.dao.user.UserDao;
import com.epam.news.dao.user.UserDaoImpl;
import com.epam.news.model.repository.PrivilegeRepository;
import com.epam.news.model.repository.RoleRepository;
import com.epam.news.model.repository.UserRepository;
import com.epam.news.service.news.NewsService;
import com.epam.news.service.news.NewsServiceImpl;
import com.epam.news.service.user.UserService;
import com.epam.news.service.user.UserServiceImpl;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@ComponentScans(value = {
        @ComponentScan("com.epam.news")
})
public class AppConfig {

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
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:ORCLCDB");
        dataSource.setUsername("oleg");
        dataSource.setPassword("password");
        return dataSource;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        ((HibernateJpaVendorAdapter) jpaVendorAdapter).setShowSql(true);
        ((HibernateJpaVendorAdapter) jpaVendorAdapter).setGenerateDdl(false);
        ((HibernateJpaVendorAdapter) jpaVendorAdapter).setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
        return jpaVendorAdapter;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(localContainerEntityManagerFactoryBean().getObject());
        return jpaTransactionManager;
    }

    @Bean
    public NewsDao newsDao() {
        return new NewsDaoImpl();
    }

    @Bean
    public NewsService newsService() {
        return new NewsServiceImpl(newsDao());
    }

    @Bean
    public UserDao userDao(){
        return new UserDaoImpl();
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl(userDao());
    }

    @Bean
    public UserRepository userRepository(){
        return new UserRepository();
    }

    @Bean
    public RoleRepository roleRepository(){
        return new RoleRepository();
    }

    @Bean
    public PrivilegeRepository privilegeRepository(){
        return new PrivilegeRepository();
    }

}
