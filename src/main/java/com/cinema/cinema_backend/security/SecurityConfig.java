package com.cinema.cinema_backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CinemaUserDetailsService cinemaUserDetailsService;
    private final JwtAuthFilter jwtAuthFilter;
    private final CustomAuthProvider customAuthProvider;


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    public SecurityConfig(CinemaUserDetailsService cinemaUserDetailsService, JwtAuthFilter jwtAuthFilter, CustomAuthProvider customAuthProvider){
        this.cinemaUserDetailsService = cinemaUserDetailsService;
        this.jwtAuthFilter = jwtAuthFilter;
        this.customAuthProvider = customAuthProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(cfg -> cfg.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(matcherRegistry -> matcherRegistry
                        .requestMatchers(HttpMethod.POST,"/register").permitAll()
                        .requestMatchers(HttpMethod.POST,"/login").permitAll()
                        .anyRequest().authenticated()
                )
                .authenticationProvider(customAuthProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .userDetailsService(cinemaUserDetailsService)
                .build();
    }


}
