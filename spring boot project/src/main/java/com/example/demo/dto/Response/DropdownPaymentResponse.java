package com.example.demo.dto.Response;

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
public class DropdownPaymentResponse {

    private String id;

    private String paymentMethodCode;

    private String bankCode;

    private String bankAccountNo;

    private String balance;

    private String statusFlag;

    private String createdBy;

    private LocalDate createdDate;

    private String updatedBy;

    private LocalDate updatedDate;
}
