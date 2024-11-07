package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.StpUser;

public interface StpUserRepository extends JpaRepository<StpUser,String> {
    
    List<StpUser> findByUsername(String username);
    
}
