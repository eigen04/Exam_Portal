package com.project.examportalbackend.configurations;

import com.project.examportalbackend.services.implementation.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    // ✅ AuthenticationManager bean (Spring Security 6 way)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // ✅ Password encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ✅ AuthenticationProvider (replacement of AuthenticationManagerBuilder)
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsServiceImpl);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    // ✅ Security filter chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> {}) // enable CORS
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        // Public endpoints
                        .requestMatchers("/api/register").permitAll()
                        .requestMatchers("/api/login").permitAll()

                        // Category
                        .requestMatchers(HttpMethod.POST, "/api/category/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/category/**").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/category/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/category/**").hasAuthority("ADMIN")

                        // Quiz
                        .requestMatchers(HttpMethod.POST, "/api/quiz/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/quiz/**").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/quiz/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/quiz/**").hasAuthority("ADMIN")

                        // Question
                        .requestMatchers(HttpMethod.POST, "/api/question/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/question/**").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/question/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/question/**").hasAuthority("ADMIN")

                        // QuizResult
                        .requestMatchers(HttpMethod.POST, "/api/quizResult/**").hasAuthority("USER")
                        .requestMatchers(HttpMethod.GET, "/api/quizResult/all/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/quizResult/**").hasAnyAuthority("USER", "ADMIN")

                        // Deny all others
                        .anyRequest().denyAll()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Add JWT filter before UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
