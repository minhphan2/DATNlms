package com.example.demo.Service.ServiceImpl.authImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.Service.Service.auth.JwtService;
import com.example.demo.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

@Service
public class JwtServiceImpl implements JwtService {
    @Value("${jwt.secret}")
    private String SECRET_KEY;
    
    @Override
    public String generateToken(User user) {
        long expiration = 1000 * 60 * 60 * 24; 
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        return Jwts.builder()
        .setSubject(user.getEmail())
        .claim("role", user.getRole().name())
        .claim("fullname", user.getFullname())
        .claim("id", user.getId())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + expiration))
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();
    }


    @Override
public boolean validateToken(String token) {
    try {
        Jwts.parserBuilder()
            .setSigningKey(SECRET_KEY.getBytes())
            .build()
            .parseClaimsJws(token);
        return true;
    } catch (Exception e) {
        return false;
    }
}

    @Override
    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder()
        .setSigningKey(SECRET_KEY.getBytes())
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
    }
}
