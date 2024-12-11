package com.example.demo.service.serviceImpl;

import org.springframework.stereotype.Service;

import com.example.demo.dto.Request.StpPaymentReq;
import com.example.demo.dto.Response.BaseResponse;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public BaseResponse createPayment(StpPaymentReq req) {
       BaseResponse response=new BaseResponse();
       return response;
    }

}
