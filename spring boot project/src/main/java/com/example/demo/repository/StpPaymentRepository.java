package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.StpPayment;

public interface StpPaymentRepository extends JpaRepository<StpPayment,String> {

    List<StpPayment> findByCreatedByAndBankCodeAndBankAccountNo(String createdBy,String bankCode,String bankAccountNo);

    List<StpPayment> findByCreatedBy(String createdBy);
}
