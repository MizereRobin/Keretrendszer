package com.example.hasznaltjarmuhu.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;


@Configuration
public class SecurityConfig {


    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("user")
                        .password("{noop}user")
                        .roles("user")
                        .build(),
                User.builder()
                        .username("manager")
                        .password("{noop}manager")
                        .roles("user", "manager")
                        .build(),
                User.builder()
                        .username("admin")
                        .password("{noop}admin")
                        .roles("user", "manager", "admin")
                        .build()
        );
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/authenticatedUser").authenticated() // antMatchers helyett
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout(logout -> logout.permitAll());

        return http.build();
    }



}
