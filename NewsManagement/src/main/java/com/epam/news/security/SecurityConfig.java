package com.epam.news.security;

import com.epam.news.security.privilege.CustomUserService;
import com.epam.news.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@ComponentScan("com.epam.news")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserService userService;
   /* @Autowired
    PasswordEncoder passwordEncoder;*/

    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

 /*   @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }*/

  /*  protected void configure(AuthenticationManagerBuilder auth)  {
        try {
            auth.jdbcAuthentication().dataSource(dataSource)
                    .passwordEncoder(new BCryptPasswordEncoder());
                  *//*  .usersByUsernameQuery("select username, password, enabled  from users where username=?")
                    .authoritiesByUsernameQuery("select username, role from user_roles where username=?");*//*
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }*/



    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(new CustomUserService(userService));
    }


    protected void configure(HttpSecurity http) throws Exception {
        http
        .cors().and()
                .authorizeRequests()
                .antMatchers("/api/news/**").permitAll()
                /*.and().formLogin().defaultSuccessUrl("/api/news", true)*/
                .and().httpBasic().and().csrf().disable();
    }
}
