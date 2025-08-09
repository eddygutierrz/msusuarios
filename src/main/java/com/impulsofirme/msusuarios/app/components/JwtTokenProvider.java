package com.impulsofirme.msusuarios.app.components;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.util.Base64;

@Component
public class JwtTokenProvider {
    private final Key signingKey;

    public JwtTokenProvider(@Value("${app.jwt.secret}") String base64Secret) {
        byte[] keyBytes = Base64.getDecoder().decode(base64Secret);
        if (keyBytes.length < 32) throw new IllegalArgumentException("Clave JWT >= 32 bytes");
        this.signingKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public Jws<Claims> parse(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token);
    }
}