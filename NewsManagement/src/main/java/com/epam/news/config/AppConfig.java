package com.epam.news.config;

import com.epam.news.dao.NewsDao;
import com.epam.news.dao.implementation.NewsDaoImpl;
import com.epam.news.service.NewsService;
import com.epam.news.service.NewsServiceImpl;
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
    public NewsDao newsDao() {
        NewsDao newsDao = new NewsDaoImpl();
        return newsDao;
    }

    @Bean
    public NewsService newsService() {
        NewsService newsService = new NewsServiceImpl(newsDao());
        return newsService;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(localContainerEntityManagerFactoryBean().getObject());
        return jpaTransactionManager;
    }
}
