package com.example.demo.service;

import com.example.demo.dto.Request.StpProductReq;
import com.example.demo.dto.Response.BaseResponse;

public interface ProductService {

    BaseResponse createProduct(StpProductReq req);
    
    BaseResponse deleteProduct(StpProductReq req);

    BaseResponse manageProduct(StpProductReq req);
} 
