package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Request.SignInReq;
import com.example.demo.dto.Request.StpUserReq;
import com.example.demo.dto.Response.BaseResponse;
import com.example.demo.dto.Response.JwtResp;
import com.example.demo.service.AuthenService;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.example.demo.config.security.jwt.JwtUtils;
import com.example.demo.config.security.service.UserDetailsImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/np/auth")
public class AuthenController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils JwtUtils;

    @Autowired
    AuthenService authenService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignInReq req) throws JsonProcessingException {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            Object jwt = JwtUtils.generateJwtToken(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());
            return ResponseEntity.ok(new JwtResp(jwt,
                    userDetails.getStpUser().getId(),
                    userDetails.getUsername(),
                    roles));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody StpUserReq req) {
        try {
            BaseResponse response = authenService.createUser(req);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
