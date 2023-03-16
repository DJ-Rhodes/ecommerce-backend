package com.djrhodes.ecommercebackend.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.AuthenticationFilter;

/**
 * Configuration of security on endpoints.
 */
@Configuration
public class WebSecurityConfig {

    /** The JWT Request Filter. */
    private JWTRequestFilter jwtRequestFilter;

    /**
     * Spring injected constructor.
     * @param jwtRequestFilter The JWT Request Filter.
     */
    public WebSecurityConfig(JWTRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    /**
     * Filter Chain to configure encryption.
     * @param httpSecurity The security object.
     * @return The built chain.
     * @throws Exception Thrown on error when configuring.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().cors().disable();
        httpSecurity.addFilterBefore(jwtRequestFilter, AuthorizationFilter.class);
        httpSecurity.authorizeHttpRequests()
                .requestMatchers("/product").permitAll()
                .requestMatchers("/auth/register").permitAll()
                .requestMatchers("/auth/login").permitAll()
                .requestMatchers("/auth/verify").permitAll()
                .anyRequest().authenticated();
        return httpSecurity.build();
    }

}
