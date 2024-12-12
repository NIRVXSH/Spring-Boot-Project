package com.example.demo.entity;

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
@Table(name="mst_subdistrict")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MstSubDistrict {
    @Id
    private String id;
    @Column(name="zip_code")
    private String zipCode;
    @Column(name="name_th")
    private String nameTh;
    @Column(name="name_en")
    private String nameEn;
    @Column(name="district_id")
    private String districtId;
    @Column(name="created_at")
    private String createdAt;
    @Column(name="updated_at")
    private String updatedAt;
    @Column(name="deleted_at")
    private String deletedAt;
}
