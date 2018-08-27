package com.app.MBox.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

@EnableWebSecurity
public class securityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    public void configure (WebSecurity web) throws Exception {

        web.ignoring().antMatchers("/resources/static/**");


    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/jquery/**/","/bootstrap/**/","/css/**","/images/**","/js/**").permitAll().antMatchers("/registration").permitAll().antMatchers("/successRegister").permitAll()
                .antMatchers("/","/test").permitAll().antMatchers("/successfullConfirm","/unSuccessfullConfirm").permitAll().antMatchers("/admin").hasAnyRole("ADMIN").anyRequest().authenticated()
        .and().formLogin().loginPage("/login").permitAll().and().logout().permitAll().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);

    }


}