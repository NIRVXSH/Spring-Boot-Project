package com.example.demo.config.security.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.hibernate.annotations.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.demo.config.security.service.UserDetailsImpl;
import com.example.demo.entity.StpUser;
import com.example.demo.utils.Constant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Configuration
@RequiredArgsConstructor
public class JwtUtils {

  private SecretKey key;

  public String generateJwtToken(Authentication authentication) throws JsonProcessingException {

    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + 300000);

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    UserToken stpUserToken = new UserToken();
    BeanUtils.copyProperties(userPrincipal.getStpUser(), stpUserToken);
    // Domain domain = new Domain();
    // BeanUtils.copyProperties(userPrincipal.getStpUser().getDomainSetup(), domain);
    // stpUserToken.setDomainSetup(domain);
    Map<String, Object> claims = new HashMap<>();
    String serializedUserDetails = objectMapper.writeValueAsString(stpUserToken);
    claims.put(Constant.USER_LOGIN, serializedUserDetails);

    // Build and sign the JWT token
    String token = Jwts.builder()
        .setSubject(userPrincipal.getUsername())
        .setIssuedAt(now)
        .setExpiration(expiryDate)
        .addClaims(claims) // Add your custom claims here
        .signWith(key)
        .compact();

    return token;
  }


    public StpUser getUserDetail(String token ) throws JsonMappingException, JsonProcessingException {
    token = AuthenTokenFilter.parseJwt(token);
    // Parse and verify the JWT token
    Claims claims = Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody();

    // Retrieve your custom claim
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.registerModule(new JavaTimeModule());
    String serializedUserDetails = (String) claims.get(Constant.USER_LOGIN);
    
    StpUser userRegister = objectMapper.readValue(serializedUserDetails,
        StpUser.class);

    return userRegister;
  }

  public String getUserNameFromJwtToken(String token) {
    return Jwts.parserBuilder().setSigningKey(key).build()
        .parseClaimsJws(token).getBody().getSubject();
  }

    public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
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
}
