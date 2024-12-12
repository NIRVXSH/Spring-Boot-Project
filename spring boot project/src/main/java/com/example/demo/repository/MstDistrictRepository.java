package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.MstDistrict;

public interface MstDistrictRepository extends JpaRepository<MstDistrict, String> {

    List<MstDistrict> findByProvinceId(String provinceId);
}
