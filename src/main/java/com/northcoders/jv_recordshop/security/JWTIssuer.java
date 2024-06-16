package com.northcoders.jv_recordshop.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RequiredArgsConstructor
@Component
@EnableConfigurationProperties(JWTProperties.class)

public class JWTIssuer {
// figuring out this properties thing below - midway thru that video
    private final JWTProperties properties;

    public String issue(Long userId, String email, List<String> roles){
        return JWT.create()
                .withSubject(String.valueOf(userId))
                .withExpiresAt(Instant.now().plus(1, ChronoUnit.DAYS))
                // will use refresh token and do minutes more than days...
                .withClaim("e", email)
                .withClaim("a", roles)
                .sign(Algorithm.HMAC256(properties.getSecretKey()));
    }
}
