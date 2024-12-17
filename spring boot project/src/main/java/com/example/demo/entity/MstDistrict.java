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
@Table(name="mst_district")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MstDistrict {

    @Id
    private String id;
    @Column(name="name_th")
    private String name;
    @Column(name="name_en")
    private String nameEn;
    @Column(name="province_id")
    private String provinceId;
    @Column(name="created_at")
    private String createdAt;
    @Column(name="updated_at")
    private String updatedAt;
    @Column(name="deleted_at")
    private String deletedAt;
}
