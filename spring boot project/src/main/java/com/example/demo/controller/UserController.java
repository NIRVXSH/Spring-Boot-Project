package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Request.StpPaymentReq;
import com.example.demo.dto.Response.BaseResponse;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/np/users")
public class UserController {

    @Autowired
    private  UserService userService;

    @PostMapping("/create-payment")
    public ResponseEntity<?> createPayment(@RequestBody StpPaymentReq req){
       try {
            BaseResponse response= userService.createPayment(req);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
