package com.companyname.services.core.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    public static final String APP_USER = "app-user";
    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(authorizeRequests -> {
            authorizeRequests
                    .requestMatchers(HttpMethod.GET, "/health").permitAll()
                    .requestMatchers(HttpMethod.POST, "/v1/users/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/v1/employees/**").hasAnyRole(APP_USER)
                    .anyRequest().authenticated();
        });
        http.oauth2ResourceServer(resourceServer -> {
            resourceServer.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter));
        });
        http.sessionManagement(sessionManagement -> {
            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });
        return http.build();
    }
}