package com.example.demo.config.JWT;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.demo.config.services.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Configuration
@RequiredArgsConstructor
public class JwtUtils {

    private String jwtSecret = "Gk2W3QdX9j2K6gI+jH9O7lzN3Q0+xO4pMlFdf9I2NEE=";
    private int jwtExpirationMs = 86400000;
    private SecretKey key;

    @PostConstruct
    public void init() {
        key = new SecretKeySpec(jwtSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
    }

    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
          return true;
        } catch (MalformedJwtException ex) {
          log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
          log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
          log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
          log.error("JWT claims string is empty");
        }
        return false;
      }

    public String getUsernameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }
}
