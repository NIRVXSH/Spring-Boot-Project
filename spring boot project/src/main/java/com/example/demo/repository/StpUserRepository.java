package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.StpUser;

public interface StpUserRepository extends JpaRepository<StpUser,String> {
    
    List<StpUser> findByUsername(String username);

    Optional<StpUser> findByUsernameAndStatusFlag(String username,String statusFlag);
    
}
