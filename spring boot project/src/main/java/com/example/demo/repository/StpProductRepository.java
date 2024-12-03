package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.StpProduct;

public interface StpProductRepository extends JpaRepository<StpProduct,String> {
    
    @Query(value = "SELECT * FROM stp_product  WHERE name = ?1 OR name_en= ?2 ",nativeQuery = true)
    List<StpProduct> findByNameOrNameEn(String name,String nameEn);
}