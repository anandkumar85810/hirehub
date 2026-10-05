package com.hirehub.hirehub.auth.security;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JwtService {

    private final JwtEncoder jwtEncoder;

    public JwtService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public String generateToken(String email, String role) {

        Instant now = Instant.now();
        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .subject(email)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(86400))
                .claim("role", role)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)
                ).getTokenValue();
    }
}
