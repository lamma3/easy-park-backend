package com.oocl.easyparkbackend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class EasyParkBackendApplication {

    @Value("${env.frontend.url}")
    private String frontendUrl;

    public static void main(String[] args) {
        SpringApplication.run(EasyParkBackendApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
						.allowedMethods("GET", "POST", "PATCH")
                        .allowedOrigins(frontendUrl);
            }
        };
    }

}