package com.fernando.sprinboot.restaurant.proyect.restaurant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // http
        //     .csrf(csrf -> csrf.disable()) // Desactivar CSRF si es API REST
        //     .authorizeHttpRequests(auth -> auth
        //         .requestMatchers("/h2-console/**").permitAll() // permitir consola H2
        //         .requestMatchers("/api/v1/roles").permitAll()
        //         .requestMatchers("/api/v1/booking-status").permitAll()    // permitir tus rutas
        //         .requestMatchers("/api/v1/extra-service").permitAll()    // permitir tus rutas
        //         .requestMatchers("/api/v1/payment-method").permitAll()    // permitir tus rutas
        //         .requestMatchers("/swagger-ui/**").permitAll()    // permitir tus rutas
        //         .requestMatchers("/swagger-ui/**").permitAll()    // permitir tus rutas
        //         .requestMatchers("/v3/api-docs/**").permitAll()    // permitir tus rutas
        //         .anyRequest().authenticated() // el resto requiere login
        //     )
        //     .headers(headers -> headers.frameOptions(frame -> frame.disable())) // necesario para H2 console
        //     .formLogin(login -> login.disable()) // desactivar login por formulario
        //     .httpBasic(basic -> basic.disable()); // desactivar basic auth si no lo usas

        http
            .csrf(csrf -> csrf.disable()) // Desactivar CSRF si es API REST
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/**").permitAll()
                .anyRequest().authenticated()
            )
                .headers(headers -> headers.frameOptions(frame -> frame.disable())) // necesario para H2 console
                .formLogin(login -> login.disable()) // desactivar login por formulario
                .httpBasic(basic -> basic.disable()); // desactivar basic auth si no lo usas
        return http.build();
    }
}
