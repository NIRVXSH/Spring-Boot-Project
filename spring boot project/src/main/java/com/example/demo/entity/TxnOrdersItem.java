package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "txn_orders_item")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TxnOrdersItem {
    @Id
    private String id;
    @Column(name="productId")
    private String productId;
    @Column(name="amount")
    private String amount;
    @Column(name="")
    private String orderId;
    @Column(name="")
    private String discountCode;
    @Column(name="")
    private LocalDateTime createDate;
    @Column(name="")
    private String status;
    @Column(name="")
    private String updateBy;
    @Column(name="")
    private String updateDate;
    @Column(name="")
    private String paymentId;
    @Column(name="")
    private String shippingId;
    @Column(name="")
    private String userId ;
}
