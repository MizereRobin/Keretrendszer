package com.example.hasznaltjarmuhu.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/home").permitAll()  // Nyilvános endpointok
                        .requestMatchers("/login", "/css/**", "/js/**").permitAll()  // Login és statikus fájlok
                        .anyRequest().authenticated()  // Minden más endpoint autentikációt igényel
                )
                .formLogin(form -> form
                        .loginPage("/login")  // Egyedi login oldal
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
