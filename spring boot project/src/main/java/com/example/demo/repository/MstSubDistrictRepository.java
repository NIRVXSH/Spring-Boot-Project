package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.MstSubDistrict;
import java.util.List;


public interface MstSubDistrictRepository extends JpaRepository<MstSubDistrict,String> {
    List<MstSubDistrict> findByDistrictId(String districtId);
}
