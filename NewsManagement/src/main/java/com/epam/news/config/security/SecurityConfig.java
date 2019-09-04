package com.epam.news.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

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

    protected void configure(AuthenticationManagerBuilder auth)  {
        try {
            auth.jdbcAuthentication().dataSource(dataSource)
                    .passwordEncoder(new BCryptPasswordEncoder())
                    .usersByUsernameQuery("select username, password, enabled  from users where username=?")
                    .authoritiesByUsernameQuery("select username, authority from authorities where username=?");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }



/*
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder)
                .withUser("user").password(passwordEncoder.encode("1"))
                .roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder.encode("1"))
                .roles("USER", "ADMIN");
    }
*/



    protected void configure(HttpSecurity http) throws Exception {

        http
        .cors().and()

       /* .authorizeRequests()
                .antMatchers("/")
                .permitAll()
                .anyRequest()
                //.permitAll()
                .fullyAuthenticated()
                .and().httpBasic().and().csrf().disable();
*/

                .authorizeRequests()
                .antMatchers("/api/news/**").permitAll()
                .antMatchers("/api/news/comments").hasRole("USER")
                .antMatchers("/**/admin/**").hasRole("ADMIN")
                .and().formLogin().defaultSuccessUrl("/api/news", true)
                .and().httpBasic().and().csrf().disable();
    }
}
