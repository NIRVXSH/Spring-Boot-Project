package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Request.BaseRequest;
import com.example.demo.dto.Request.StpPaymentReq;
import com.example.demo.dto.Request.StpShippingAddressReq;
import com.example.demo.dto.Response.BaseResponse;
import com.example.demo.dto.Response.DropdownPaymentResponse;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.JsonSerializable.Base;

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

    @PostMapping("/delete-payment")
    public ResponseEntity<?> deletePayment(@RequestBody StpPaymentReq req){
       try {
            BaseResponse response= userService.createPayment(req);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @PostMapping("/getDropdownPayment")
    public ResponseEntity<?> getDropdownPayment(@RequestBody BaseRequest req) throws IOException {
        try {
            List<DropdownPaymentResponse> response = userService.getDropdownPayment(req);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/create-shipping-address")
    public ResponseEntity<?> createShipping(@RequestBody StpShippingAddressReq req){
       try {
            BaseResponse response= userService.createShippingAddress(req);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/delete-shipping-address")
    public ResponseEntity<?> deleteShipping(@RequestBody StpPaymentReq req){
       try {
            BaseResponse response= userService.createPayment(req);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @PostMapping("/getDropdownShipping-address")
    public ResponseEntity<?> getDropdownShipping(@RequestBody BaseRequest req) throws IOException {
        try {
            List<DropdownPaymentResponse> response = userService.getDropdownPayment(req);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
