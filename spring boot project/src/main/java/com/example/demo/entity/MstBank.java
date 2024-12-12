package com.example.demo.entity;

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
@Table(name = "mst_bank")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MstBank {
    @Id
    private String id;
    @Column(name="bank_code")
    private String bankCode;
    @Column(name="bank_name")
    private String bankName;
    @Column(name="bank_name_en")
    private String bankNameEn;
}
