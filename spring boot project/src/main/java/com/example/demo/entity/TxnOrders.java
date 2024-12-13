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
@Table(name = "txn_orders")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TxnOrders {


    @Id
    private String id;

    @Column(name="status")
    private String status;

    @Column(name="payment_id")
    private String paymentId;

    @Column(name="shipping_id")
    private String shippingId;


    @Column(name="user_id")
    private String userId;

    @Column(name="create_date")
    private LocalDateTime createDate; 

    @Column(name="update_by")
    private String updateBy;

    @Column(name="update_date")
    private LocalDateTime updateDate;  
}
