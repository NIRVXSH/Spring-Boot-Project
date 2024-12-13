package com.example.demo.dto.Request;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
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

    private String id;

    private String status;

    private String paymentId;

    private String shippingId;

    private String userId;

    private LocalDateTime createDate;

    private String updateBy;

    private LocalDateTime updateDate;

    List<OrderItem> orderItems;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class OrderItem implements Serializable {

        private String id;

        private String productId;

        private String amount;

        private String orderId;

        private String discountCode;

        private LocalDateTime createDate;

        private String status;

        private String updateBy;

        private String updateDate;

        private String paymentId;

        private String shippingId;

        private String userId;
    }

}