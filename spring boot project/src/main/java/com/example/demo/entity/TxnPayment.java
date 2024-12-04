package com.example.demo.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
@Table(name = "txn_payment")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TxnPayment {

    @Id
    private String id;

    @Column(name="payment_method_code")
    private String paymentMethodCode;

    @Column(name="bank_code")
    private String bankCode;

    @Column(name="bank_account_no")
    private String bankAccountNo;

    @Column(name="balance")
    private String balance;

    @Column(name="status")
    private String status;

    @Column(name = "created_by")
    private String createdBy;

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private LocalDate createdDate;

    @Column(name = "updated_by")
    private String updatedBy;

    @LastModifiedDate
    @Column(name = "updated_date")
    private LocalDate updatedDate;

}
