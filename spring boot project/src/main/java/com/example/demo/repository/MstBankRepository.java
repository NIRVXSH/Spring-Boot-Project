package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.MstBank;

public interface MstBankRepository extends JpaRepository<MstBank,String>  {

    
}
