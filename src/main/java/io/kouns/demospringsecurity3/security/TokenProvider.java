package io.kouns.demospringsecurity3.security;

import io.jsonwebtoken.*;
import io.kouns.demospringsecurity3.dto.JwtAuthenticationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TokenProvider {

    @Value("${jwt.jwtSecret}")
    private String jwtSecret;

    @Value("${jwt.jwtRefreshSecret}")
    private String jwtRefreshSecret;

    @Value("${jwt.jwtExpirationInMs}")
    private long jwtExpirationInMs;

    @Value("${jwt.jwtRefreshExpirationInMs}")
    private long jwtRefreshExpirationInMs;


    private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);


    public TokenProvider() {

    }


    public JwtAuthenticationResponse generateToken(Authentication authentication) {
        return generateToken((UserPrincipal) authentication.getPrincipal());
    }

    public JwtAuthenticationResponse generateToken(UserPrincipal userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
        Date refreshExpiryDate = new Date(now.getTime() + jwtRefreshExpirationInMs);

        Map<String, Object> claims = new HashMap<>();
        List<String> roles = new ArrayList<>();
        userDetails.getAuthorities().forEach(role -> {
            roles.add(role.getAuthority());
        });


        claims.put("permissions", roles.toArray(new String[0]));

        // JWT for permission
        String tokenPermission = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret.getBytes())
                .compact();

        String token = Jwts.builder()
                .setSubject(userDetails.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret.getBytes())
                .compact();


        // JWT for refresh token
        Map<String, Object> claimsRefresh = new HashMap<>();
        String accessToken = Jwts.builder()
                .setClaims(claimsRefresh)
                .setSubject(userDetails.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(refreshExpiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtRefreshSecret.getBytes())
                .compact();

        return new JwtAuthenticationResponse(token, accessToken, expiryDate, tokenPermission);
    }

    public String getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret.getBytes())
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        return validateJWT(authToken, jwtSecret);
    }

    private boolean validateJWT(String authToken, String jwtSecret) {
        Jwts.parser().setSigningKey(jwtSecret.getBytes()).parseClaimsJws(authToken);
        return true;
    }


}
