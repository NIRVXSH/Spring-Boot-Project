package com.example.demo.service;

import com.example.demo.dto.Request.StpUserReq;
import com.example.demo.dto.Response.BaseResponse;

public interface AuthenService {
    BaseResponse createUser(StpUserReq req);
}
