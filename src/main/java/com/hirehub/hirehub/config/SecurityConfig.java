package com.hirehub.hirehub.config;

import com.hirehub.hirehub.exception.SecurityErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Configuration
public class SecurityConfig {

    private final String secret =
            "HireHubSuperSecretKeyForJwtAuthentication2026Secure";
    private final SecurityErrorHandler securityErrorHandler;

    public SecurityConfig(SecurityErrorHandler securityErrorHandler) {
        this.securityErrorHandler = securityErrorHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtDecoder jwtDecoder() {

        SecretKey secretKey = new SecretKeySpec(
                secret.getBytes(StandardCharsets.UTF_8),
                "HmacSHA256"
        );

        return NimbusJwtDecoder
                .withSecretKey(secretKey)
                .build();
    }

    @Bean
    public JwtEncoder jwtEncoder() {

        SecretKey secretKey = new SecretKeySpec(
                secret.getBytes(StandardCharsets.UTF_8),
                "HmacSHA256"
        );

        return NimbusJwtEncoder
                .withSecretKey(secretKey)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {

        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        // Public authentication APIs
                        .requestMatchers("/api/auth/**")
                        .permitAll()

                        // Admin APIs
                        .requestMatchers("/api/admin/**")
                        .hasRole("ADMIN")

                        // View jobs - all authenticated users
                        .requestMatchers(HttpMethod.GET, "/api/jobs/**")
                        .hasAnyRole("CANDIDATE", "COMPANY", "RECRUITER", "ADMIN")

                        // Create jobs
                        .requestMatchers(HttpMethod.POST, "/api/jobs/**")
                        .hasAnyRole("COMPANY", "RECRUITER")

                        // Update jobs
                        .requestMatchers(HttpMethod.PUT, "/api/jobs/**")
                        .hasAnyRole("COMPANY", "RECRUITER")

                        // Delete jobs
                        .requestMatchers(HttpMethod.DELETE, "/api/jobs/**")
                        .hasAnyRole("COMPANY", "RECRUITER")

                        // Application APIs
                        .requestMatchers("/api/applications/**")
                        .authenticated()

                        // Everything else requires authentication
                        .anyRequest()
                        .authenticated()
                )
                .exceptionHandling(expections -> expections
                        .authenticationEntryPoint(securityErrorHandler)
                        .accessDeniedHandler(securityErrorHandler)
                )
                .oauth2ResourceServer(
                        oauth2 -> oauth2.jwt(
                                jwt -> jwt.jwtAuthenticationConverter(
                                        jwtAuthenticationConverter()
                                )
                        )
                );

        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {

        JwtGrantedAuthoritiesConverter authoritiesConverter =
                new JwtGrantedAuthoritiesConverter();

        authoritiesConverter.setAuthoritiesClaimName("role");
        authoritiesConverter.setAuthorityPrefix("ROLE_");

        JwtAuthenticationConverter converter =
                new JwtAuthenticationConverter();

        converter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);

        return converter;
    }
}