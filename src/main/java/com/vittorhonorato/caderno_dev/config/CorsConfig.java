package com.vittorhonorato.caderno_dev.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import java.util.Arrays;


@Configuration
public class CorsConfig {
    private final String[] allowedOrigins = {
            "http://localhost:4200",
            "https://caderno-dev-frontend.onrender.com"
    };

    private final String[] allowedMethods = {
            "GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"
    };

    private final String[] allowedHeaders = {
            "Authorization", "Content-Type"
    };

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cors = new CorsConfiguration();
        cors.setAllowedOrigins(Arrays.stream(allowedOrigins).toList());
        cors.setAllowedMethods(Arrays.stream(allowedMethods).toList());
        cors.setAllowedHeaders(Arrays.stream(allowedHeaders).toList());

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cors);

        return source;
    }
}
