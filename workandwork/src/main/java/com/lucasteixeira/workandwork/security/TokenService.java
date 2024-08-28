package com.lucasteixeira.workandwork.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.lucasteixeira.workandwork.domain.Pessoa;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String keySecret;

    public String generateToken(Pessoa pessoa) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(keySecret);

            return JWT.create()
                    .withIssuer("work-and-work")
                    .withSubject(pessoa.getEmail())
                    .withExpiresAt(expirantionDate())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro while generating token", e);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(keySecret);
            return JWT.require(algorithm)
                    .withIssuer("work-and-work")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }

    }

    public Instant expirantionDate() {
        return LocalDateTime.now().plusDays(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
