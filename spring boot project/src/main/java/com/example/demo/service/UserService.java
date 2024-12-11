package com.example.demo.service;

import com.example.demo.dto.Request.StpPaymentReq;
import com.example.demo.dto.Response.BaseResponse;

public interface UserService {
    BaseResponse createPayment( StpPaymentReq req);
}
