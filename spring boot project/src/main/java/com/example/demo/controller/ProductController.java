package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Request.StpProductReq;

@RestController
@RequestMapping("/np/product")
public class ProductController {
    @PostMapping("/create")
    public ResponseEntity<?> createProduct(StpProductReq req){
        return ResponseEntity.ok(null);
    }
}
