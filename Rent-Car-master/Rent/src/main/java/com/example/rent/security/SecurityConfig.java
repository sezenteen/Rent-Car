package com.example.rent.security;

import com.example.rent.service.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final UserService userService;

    @Autowired
    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Allow public access to specific paths
                        .requestMatchers(
                                "/register",
                                "/login",
                                "/products/**",  // Ensure public product pages
                                "/images/**",    // Serve static images
                                "/css/**",       // Serve static CSS
                                "/js/**",        // Serve static JavaScript
                                "/"
                        ).permitAll()
                        // Restrict access to user-specific pages
                        .requestMatchers("/users/**").authenticated()
                        .anyRequest().authenticated() // Enforce authentication for any other requests
                )
                .formLogin(form -> form
                        .loginPage("/login")         // Custom login page
                        .defaultSuccessUrl("/", true) // Redirect to homepage on login success
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")         // Custom logout URL
                        .logoutSuccessUrl("/")       // Redirect to homepage on logout
                        .invalidateHttpSession(true) // Invalidate session
                        .deleteCookies("JSESSIONID") // Delete session cookie
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
