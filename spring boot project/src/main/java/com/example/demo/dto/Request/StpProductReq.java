package com.example.demo.dto.Request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StpProductReq {

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

    private LocalDate createDate;

    private String updateBy;

    private LocalDate updateDate;

    private String statusFlag;
}
