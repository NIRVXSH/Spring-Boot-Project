package com.example.demo.entity;

import java.time.LocalDateTime;

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
@Table(name = "stp_shipping_address")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StpShippingAddress {
    @Id
    private String id;

    @Column(name = "house_no")
    private String houseNo;

    @Column(name = "address")
    private String address;

    @Column(name = "province")
    private String province;

    @Column(name = "district")
    private String district;

    @Column(name = "subDistrict")
    private String subDistrict;

    @Column(name = "zipCode")
    private String zipCode;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by")
    private String createdBy;

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_by")
    private String updatedBy;

    @LastModifiedDate
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
}
