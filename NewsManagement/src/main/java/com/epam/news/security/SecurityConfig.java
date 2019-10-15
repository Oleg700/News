package com.epam.news.security;



import com.epam.news.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Custom implementation of {@link WebSecurityConfigurerAdapter}
 * to set rules for Spring Security access.
 *
 * @author Oleg Aliyev
 */
@Configuration
@EnableWebSecurity
@ComponentScan("com.epam.news")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * class is used to get username and password from database.
     */
    @Autowired
    private UserService userService;

    @Override
    protected void configure(final AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(new CustomUserService(userService));
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http
                .httpBasic().and().csrf().disable()
                .cors().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/news/**").permitAll();
    }
}
