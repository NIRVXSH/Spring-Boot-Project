package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/np/product")
public class ProductController {
    @PostMapping("/create")
    public ResponseEntity<?> createProduct(){
        return ResponseEntity.ok(null);
    }
}
