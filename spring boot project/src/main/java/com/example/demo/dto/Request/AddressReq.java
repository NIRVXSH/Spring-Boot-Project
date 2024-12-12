package com.example.demo.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressReq extends BaseRequest {
    private String provinceId;
    private String districtId;
    private String subdistrictId;
}
