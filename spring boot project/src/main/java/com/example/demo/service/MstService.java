package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Request.AddressReq;
import com.example.demo.dto.Response.DropdownDistrictResponse;
import com.example.demo.dto.Response.DropdownProvinceResponse;
import com.example.demo.dto.Response.DropdownResponse;
import com.example.demo.dto.Response.DropdownSubDistrictResponse;
import com.example.demo.entity.MstBank;
import com.example.demo.repository.MstBankRepository;
import com.example.demo.repository.MstDistrictRepository;
import com.example.demo.repository.MstProvinceRepository;
import com.example.demo.repository.MstSubDistrictRepository;
import com.example.demo.util.UtilService;

@Service
public class MstService {

    @Autowired
    private MstBankRepository bankRepository;

    @Autowired
    private MstProvinceRepository provinceRepository;

    @Autowired
    private MstDistrictRepository districtRepository;

    @Autowired
    private MstSubDistrictRepository subDistrictRepository;
    
    @Autowired
    private UtilService utilService;

    public List<DropdownResponse> getDropdownBank() {
        List<DropdownResponse> bank = bankRepository.findAll().stream()
                .map(bank1 -> DropdownResponse.builder().id(bank1.getId()).code(bank1.getBankCode())
                        .name(bank1.getBankName()).nameEn(bank1.getBankNameEn()).build())
                .toList();
        return bank;
    }

    public List<DropdownProvinceResponse> getDropdownProvince() {
        Map<String, List<String>> transformationRules = new HashMap<>();
                List<DropdownProvinceResponse> province = provinceRepository.findAll().stream()
                .map(province1 -> utilService.mapEntityToDto(province1, DropdownProvinceResponse.class, transformationRules))
                .toList();
        return province;
    }

    public List<DropdownDistrictResponse> getDropdownDistrict(AddressReq req) {
        List<DropdownDistrictResponse> district = districtRepository.findByProvinceId(req.getProvinceId()).stream()
                .map(district1 -> utilService.mapEntityToDto(district1,DropdownDistrictResponse.class, null))
                .toList();
        return district;
    }

    public List<DropdownSubDistrictResponse> getDropdownSubDistrict(AddressReq req) {
        List<DropdownSubDistrictResponse> subDistrict = subDistrictRepository.findByDistrictId(req.getDistrictId()).stream()
                .map(subDistrict1 -> utilService.mapEntityToDto(subDistrict1,DropdownSubDistrictResponse.class, null))
                .toList();
        return subDistrict;
    }
}
