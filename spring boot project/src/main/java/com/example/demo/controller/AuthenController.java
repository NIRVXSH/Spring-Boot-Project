package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Request.StpUserReq;
import com.example.demo.dto.Response.BaseResponse;
import com.example.demo.service.AuthenService;

@RestController
@RequestMapping("/np/auth")
public class AuthenController {


    @Autowired
    private AuthenService authenService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody StpUserReq req){
        try {
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody StpUserReq req){
        try {
            BaseResponse response= authenService.createUser(req);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
