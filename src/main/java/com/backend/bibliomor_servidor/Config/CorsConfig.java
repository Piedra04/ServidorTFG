package com.backend.bibliomor_servidor.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(
                            "http://localhost:4200",
                            "http://bibliomor.pedro.dawmor.cloud.s3-website-us-east-1.amazonaws.com",
                            "http://bibliomor.pedro.dawmor.cloud"
                        )
                        .allowedMethods("*")
                        .allowedHeaders("*");
            }
        };
    }
}
