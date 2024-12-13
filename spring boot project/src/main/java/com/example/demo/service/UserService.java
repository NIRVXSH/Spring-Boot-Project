package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.Request.BaseRequest;
import com.example.demo.dto.Request.StpPaymentReq;
import com.example.demo.dto.Request.StpShippingAddressReq;
import com.example.demo.dto.Response.BaseResponse;
import com.example.demo.dto.Response.DropdownPaymentResponse;

public interface UserService {
    BaseResponse createPayment( StpPaymentReq req);

    BaseResponse deletePayment( StpPaymentReq req);

    List<DropdownPaymentResponse> getDropdownPayment(BaseRequest req);


    BaseResponse createShippingAddress( StpShippingAddressReq req);


}
