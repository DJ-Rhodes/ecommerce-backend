package com.djrhodes.ecommercebackend.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration of security on endpoints.
 */
@Configuration
public class WebSecurityConfig {

    /**
     * Filter Chain to configure encryption.
     * @param httpSecurity The security object.
     * @return The built chain.
     * @throws Exception Thrown on error when configuring.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        //TODO: Proper Authentication!!
        httpSecurity.csrf().disable().cors().disable();
        httpSecurity.authorizeHttpRequests().anyRequest().permitAll();
        return httpSecurity.build();
    }

}
