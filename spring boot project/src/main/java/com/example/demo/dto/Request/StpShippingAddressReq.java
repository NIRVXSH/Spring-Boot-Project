package com.example.demo.dto.Request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StpShippingAddressReq extends BaseRequest {

    private String id;

    private String houseNo;

    private String address;

    private String province;

    private String district;

    private String subDistrict;

    private String zipCode;

    private String mobileNo;

    private String status;

    private String createdBy;

    private LocalDate createdDate;

    private String updatedBy;

    private LocalDate updatedDate;
}
