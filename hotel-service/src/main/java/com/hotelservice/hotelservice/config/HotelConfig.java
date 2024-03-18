package com.hotelservice.hotelservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class HotelConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {

        security.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api")
                        .authenticated()
                        .requestMatchers("/api")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                ).oauth2ResourceServer((oauth) -> oauth.jwt(Customizer.withDefaults()));

        return security.build();
    }
}
