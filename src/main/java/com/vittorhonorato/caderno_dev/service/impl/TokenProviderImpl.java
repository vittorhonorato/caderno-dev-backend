package com.vittorhonorato.caderno_dev.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.vittorhonorato.caderno_dev.service.TokenProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class TokenProviderImpl implements TokenProvider {
    private static final String SECRET = "a";
    private static final String ISSUER = "";
    @Override
    public String generateToken(Authentication authentication) {

        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return JWT.create()
                .withSubject(authentication.getName())
                .withClaim("roles", roles)
                .withIssuer(ISSUER)
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusSeconds(3600))
                .sign(algorithm);
    }

    @Override
    public String getSubject(String token) {
        return validToken(token).getSubject();
    }

    @Override
    public boolean isValid(String token) {
        try {
            validToken(token);
            return true;
        } catch (JWTVerificationException ex) {
            return false;
        }
    }

    private DecodedJWT validToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build();

        return verifier.verify(token);
    }
}
