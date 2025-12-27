package com.example.demo.Service.Service.auth;
import org.springframework.stereotype.Service;
import com.example.demo.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

@Service
public interface JwtService {
    String generateToken(User user);
    boolean validateToken(String token);
    String getEmailFromToken(String token);
    
}
