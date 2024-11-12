package com.example.demo.config.security.jwt;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class UserToken {

    private String id;
    private String email;
    private String username;
    private String password;
    private String mobileNumber;
    
    private String allowInitial; // Y, N
    private String flagUser; // Special, Normal

    private User createBy;
    private LocalDateTime createDate;
    private User updateBy;
    private LocalDateTime updateDate;


    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class User {
        private String name;
        private String surName;
        private String nameEn;
        private String surNameEn;
    }

}
