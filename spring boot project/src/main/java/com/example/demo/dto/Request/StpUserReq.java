package com.example.demo.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private byte[] profilePic;

    private String roleId;
}
