package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.StpShippingAddress;

public interface StpShippingRepository extends JpaRepository<StpShippingAddress, String> {

    @Query(value = "SELECT * FROM stp_shipping_address WHERE created_by = :createdBy " +
            "AND address LIKE CONCAT('%', :address, '%') " +
            "AND house_no LIKE CONCAT('%', :houseNo, '%') " +
            "AND zip_code = :zipCode " +
            "AND mobile_no = :mobileNo " +
            "AND province = :province " +
            "AND district = :district " +
            "AND sub_district = :subDistrict " +
            "AND status = 'ACTIVE'", nativeQuery = true)
    List<StpShippingAddress> findByCreatedByAndOther(
            @Param("createdBy") String createdBy,
            @Param("address") String address,
            @Param("houseNo") String houseNo,
            @Param("zipCode") String zipCode,
            @Param("mobileNo") String mobileNo,
            @Param("province") String province,
            @Param("district") String district,
            @Param("subDistrict") String subDistrict);

}
