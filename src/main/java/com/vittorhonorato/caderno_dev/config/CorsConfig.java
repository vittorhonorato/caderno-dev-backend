package com.vittorhonorato.caderno_dev.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import java.util.Arrays;


@Configuration
public class CorsConfig {
    private String[] allowedOrigins = {"http://localhost:4200"};
    private String[] allowedMethods = {"GET", "POST", "PUT", "PATCH", "DELETE"};

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cors = new CorsConfiguration();
        cors.setAllowedOrigins(Arrays.stream(allowedOrigins).toList());
        cors.setAllowedMethods(Arrays.stream(allowedMethods).toList());

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cors);

        return source;
    }
}
