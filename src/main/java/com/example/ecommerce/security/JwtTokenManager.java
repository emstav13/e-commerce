package com.example.ecommerce.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Calendar;

public class JwtTokenManager {

    public static String generateToken(String email) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 30);

        return Jwts.builder()
                .subject(email)
                .expiration(calendar.getTime())
                .signWith(secretKey(), Jwts.SIG.HS256)
                .compact();
    }

    public static String getUser(String token) {

        Claims claims = Jwts.parser()
                .verifyWith(secretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    private static SecretKey secretKey() {

        String secret =
                "wzUpGa9k4LTV3SHuY8qVrt6wOENkfdes5vLHVc1ex6581Iiq";

        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(secret)
        );
    }
}