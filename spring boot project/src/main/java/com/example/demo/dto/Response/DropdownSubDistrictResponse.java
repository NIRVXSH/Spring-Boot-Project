package com.example.demo.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DropdownSubDistrictResponse {
    private String id;
    private String districtId;
    private String zipCode;
    private String name;
    private String nameEn;
}
