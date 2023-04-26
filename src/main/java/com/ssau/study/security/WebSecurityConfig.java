package com.ssau.study.security;

import com.ssau.study.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
@AllArgsConstructor
public class WebSecurityConfig {

    private final UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { // (2)
        http
                .cors().configurationSource(corsConfigurationSource()).and()
                .csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/login","/register").permitAll()
                        .requestMatchers("/api/v2/**").hasAnyRole("USER","ADMIN")
                        .anyRequest().authenticated() // (5)
                )
//                .formLogin((form) -> form
//                        .loginPage("/login.html") // (6)
//                        .loginProcessingUrl("/login") // (6)
//                        .defaultSuccessUrl("/index.html") // (6)
//                        .failureUrl("/login.html?error=true") // (6)
//                        .permitAll()
//                )
//                .logout(LogoutConfigurer::permitAll)
                .httpBasic();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() { // (8)
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userService::getUserByUsername;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins (List.of("http://localhost:4200"));
        configuration.setAllowedMethods (Arrays.asList("PUT","GET", "POST", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders (Arrays.asList("Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization")); configuration.setAllowCredentials (true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}