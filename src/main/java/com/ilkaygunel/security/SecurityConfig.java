package com.ilkaygunel.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(csrf -> csrf.disable())
                //.authorizeHttpRequests(auth -> auth.requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll())
                //.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/generateToken").permitAll())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/**").permitAll())
                //.authorizeHttpRequests(auth -> auth.requestMatchers("/bill/**").hasRole("SALES_PERSON"))
                //.authorizeHttpRequests(auth -> auth.requestMatchers("/products/**").hasRole("SALES_PERSON"))
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

}
