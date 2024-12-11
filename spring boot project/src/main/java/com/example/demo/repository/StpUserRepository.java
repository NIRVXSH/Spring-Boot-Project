package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.StpUser;

public interface StpUserRepository extends JpaRepository<StpUser,String> {
    @Query(value = "SELECT u.* FROM stp_user u WHERE u.username = ?1", nativeQuery = true)
    List<StpUser> findUserByUsername(String username);

    Optional<StpUser> findByUsername(String username);
    
}
