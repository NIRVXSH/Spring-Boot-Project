package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.StpProduct;

public interface StpProductRepository extends JpaRepository<StpProduct,String> {
    
    @Query(value = "SELECT * FROM stp_product  WHERE ( name = ?1 OR name_en= ?2 )AND created_by = ?3 AND status_flag = 'ACTIVE' ",nativeQuery = true)
    List<StpProduct> findByNameOrNameEnAndCreateBy(String name,String nameEn,String createBy);

    @Query(value = "SELECT * FROM stp_product  WHERE ( name = ?1 OR name_en= ?2 )AND id != ?3 ",nativeQuery = true)
    List<StpProduct> findByNameOrNameEnAndNotId(String name,String nameEn,String productId);
}