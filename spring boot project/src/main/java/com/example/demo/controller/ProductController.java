package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Request.StpProductReq;
import com.example.demo.dto.Response.BaseResponse;
import com.example.demo.entity.StpProduct;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/np/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody StpProductReq req){
        try {
            BaseResponse response=productService.createProduct(req);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        
        
    }
}
