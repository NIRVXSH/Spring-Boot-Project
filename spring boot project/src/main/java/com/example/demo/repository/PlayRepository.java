package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Play;

public interface PlayRepository extends JpaRepository<Play, String> {

}
