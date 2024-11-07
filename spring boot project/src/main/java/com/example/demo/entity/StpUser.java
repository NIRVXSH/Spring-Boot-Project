package com.example.demo.entity;




import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="stp_user")
public class StpUser  {

    @Id
    private String id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name="name_en")
    private String nameEn;

    @Column(name="surname_en")
    private String surnameEn;

    @Column(name="nickname")
    private String nickName;

    @Column(name="nickname_en")
    private String nickNameEn;

    @Column(name="date_of_birth")
    private String dateOfBirth;

    @Column(name="bank_account_no")
    private String bankAccountNo;

    @Column(name="bank_code")
    private String bankCode;

    @Column(name="create_date")
    private LocalDate createDate;

    @Column(name="update_by")
    private String updateBy;

    @Column(name="update_date")
    private LocalDate updateDate;

    @Column(name="status_flag")
    private String statusFlag;



}
