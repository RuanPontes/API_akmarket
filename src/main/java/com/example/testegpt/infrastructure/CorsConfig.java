package com.example.testegpt.infrastructure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200") // Substitua por suas URLs permitidas
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS") // Substitua por seus métodos permitidos
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
