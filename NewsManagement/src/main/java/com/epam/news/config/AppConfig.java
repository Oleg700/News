package com.epam.news.config;

import com.epam.news.dao.user.UserDao;
import com.epam.news.dao.user.UserDaoImpl;
import com.epam.news.security.RestResponseEntityExceptionHandler;
import com.epam.news.service.user.UserService;
import com.epam.news.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
@PropertySource(value = {"classpath:application.properties"})
@EnableTransactionManagement
@ComponentScans(value = {
        @ComponentScan("com.epam.news")
})
public class AppConfig {

    @Autowired
    Environment environment;

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource =
                new ReloadableResourceBundleMessageSource();
        messageSource.addBasenames("classpath:SecurityExceptions");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }


    @Bean
    public ResponseEntityExceptionHandler responseEntityExceptionHandler() {
        return new RestResponseEntityExceptionHandler();
    }

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


}
