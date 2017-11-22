package com.clarkez.oauthfesolo.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Configuration
//@EnableWebSecurity
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

    private final String uri = "/*";

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().httpStrictTransportSecurity().disable();

        // Authorize sub-folders permissions
        //http.antMatcher(uri).authorizeRequests().anyRequest().permitAll();
    }
}