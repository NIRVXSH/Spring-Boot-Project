package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.StpPayment;

public interface StpPaymentRepository extends JpaRepository<StpPayment,String> {

}
