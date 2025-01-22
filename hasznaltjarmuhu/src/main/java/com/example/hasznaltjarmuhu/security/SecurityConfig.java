package com.example.hasznaltjarmuhu.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {



        //login nélkül
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/",
                                //"/login",
                                "/register",
                                "/submit",
                                "/hirdetesek",
                                "/hirdet",
                                //"/users",
                                "/home").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}