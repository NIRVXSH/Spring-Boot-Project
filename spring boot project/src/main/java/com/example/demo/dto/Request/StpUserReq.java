package com.example.demo.dto.Request;

import java.time.LocalDate;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StpUserReq {

    private String id;

    private String username;

    private String password;

    private String name;

    private String surname;

    private String nameEn;

    private String surnameEn;

    private String nickName;

    private String nickNameEn;

    private String dateOfBirth;

    private String bankAccountNo;

    private String bankCode;
}
