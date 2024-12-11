package com.example.demo.dto.Request;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TxnOrderReq implements Serializable {

    private String productId;

    private int amount;

    private String status;

    private String paymentId;

    private String shippingId;

    private String discountCode;

    private String ownerId;

    private String userId;

    private LocalDateTime createDate; 

    private String updateBy;

    private LocalDateTime updateDate;  
}