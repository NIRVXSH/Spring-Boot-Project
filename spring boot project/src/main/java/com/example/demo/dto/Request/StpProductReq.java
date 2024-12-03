package com.example.demo.dto.Request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StpProductReq {

    private String id;

    private String categoryCode;

    private String name;

    private String nameEn;

    private Float price;

    private String discountCode;

    private Integer available;

    private String createdBy;

    private LocalDate createdDate;

    private String updatedBy;

    private LocalDate updatedDate;
    
    private String statusFlag;
}