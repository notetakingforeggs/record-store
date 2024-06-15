/*
package com.northcoders.jv_recordshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.text.DecimalFormat;


@Configuration
@EnableWebSecurity
public class SecSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user1 = User.withUsername("user1")
                .password(passwordEncoder().encode("user1Pass"))
                .roles("USER")
                .build();
        UserDetails user2 = User.withUsername("user2")
                .password(passwordEncoder().encode("user2Pass"))
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("adminPass"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user1, user2, admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disabling CSRF
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Requires ADMIN role
                        .requestMatchers("/anonymous*").anonymous() // Allows anonymous access
                        .requestMatchers("/login*").permitAll() // Allows everyone to access login
                        .anyRequest().authenticated() // All other requests require authentication
                        .formLogin(formLogin -> formLogin
                                .loginPage("/login.html") // Custom login page
                                .loginProcessingUrl("/perform_login") // Login processing URL
                                .defaultSuccessUrl("/homepage.html", true) // Redirect on successful login
                                .failureUrl("/login.html?error=true") // Redirect on login failure
                                .failureHandler(authenticationFailureHandler()) // Custom failure handler
                        )
                        .logout(logout -> logout
                                .logoutUrl("/perform_logout") // Logout URL
                                .deleteCookies("JSESSIONID") // Delete JSESSIONID cookie
                                .logoutSuccessHandler(logoutSuccessHandler()) // Custom logout success handler
                        );

                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}*/
