package com.impulsofirme.msusuarios.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Deshabilita CSRF para pruebas
            .authorizeRequests()
            .anyRequest().permitAll(); // Permite acceso público a todos los endpoints

        return http.build();
    }
}