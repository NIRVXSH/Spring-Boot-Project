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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stp_product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StpProduct {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "category_code")
    private String categoryCode;

    @Column(name = "name")
    private String name;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "price")
    private Float price;

    @Column(name = "discount_code")
    private String discountCode;

    @Column(name = "available")
    private Integer available;

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

    @Column(name = "status_flag")
    private String statusFlag;

    @Column(name = "image_product", columnDefinition = "BYTEA")
    private byte[] imageProduct;
}

