package com.fernando.sprinboot.restaurant.proyect.restaurant.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("RESTFUL API about a restaurant")
                .version("1.0.0")
                .description("""
                        This API allows you to manage a restaurant system, including entities such as roles, users, bookings,
                        tables, extra services, payment methods, and more.
                        You can perform CRUD operations to manage bookings.
                        This project is developed using the best Spring Boot practices. It uses layered architecture, DTOs, mappers, services,
                        repositories, and controllers to ensure a clean and maintainable codebase.
                        The API is all about practicing and applying Spring Boot concepts effectively, hoping it improves my Spring Boot skills.
                        Besides, this will be my first public project using Spring Boot, so I hope it's useful for me to learn, and hopefully showcase my skills.
                        """)
            );
    }

    @Bean
    public GroupedOpenApi v1Group() {
        return GroupedOpenApi.builder()
            .group("v1")
            .pathsToMatch("/api/v1/**")
            .build();
    }
}
