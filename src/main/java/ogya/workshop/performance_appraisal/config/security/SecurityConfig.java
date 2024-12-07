package ogya.workshop.performance_appraisal.config.security;

import jakarta.servlet.http.HttpServletRequest;
import ogya.workshop.performance_appraisal.config.security.Auth.AuthService;
import ogya.workshop.performance_appraisal.config.security.jwt.JwtAuthComponent;
import ogya.workshop.performance_appraisal.config.security.jwt.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public JwtAuthComponent JwtAuthComponent(
            JwtService jwtService,
            AuthService userDetailsService,
            HandlerExceptionResolver handlerExceptionResolver
    ) {
        return new JwtAuthComponent(jwtService, userDetailsService, handlerExceptionResolver);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthComponent JwtAuthComponent) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()// Allow public access to the auth endpoints
                        .anyRequest().authenticated()// All other requests need authentication

                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Disable sessions, stateless authentication
                )
                .addFilterBefore(JwtAuthComponent, UsernamePasswordAuthenticationFilter.class); // Add JWT filter before the default authentication filter

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
