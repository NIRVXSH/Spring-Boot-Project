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
    private PlayService playService;

    public List<DropdownResponse> getDropdownBank() {
        List<DropdownResponse> bank = bankRepository.findAll().stream()
                .map(bank1 -> DropdownResponse.builder().id(bank1.getId()).code(bank1.getBankCode())
                        .name(bank1.getBankName()).nameEn(bank1.getBankNameEn()).build())
                .toList();
        return bank;
    }

    public List<DropdownProvinceResponse> getDropdownProvince() {
        // List<DropdownProvinceResponse> province = provinceRepository.findAll().stream()
        //         .map(province1 -> DropdownProvinceResponse.builder().id(province1.getId()).name(province1.getNameTh())
        //                 .nameEn(province1.getNameEn()).build())
        //         .toList();
        Map<String, List<String>> transformationRules = new HashMap<>();
                List<DropdownProvinceResponse> province = provinceRepository.findAll().stream()
                .map(province1 -> playService.mapEntityToDto(province1, DropdownProvinceResponse.class, transformationRules))
                .toList();
        return province;
    }

    public List<DropdownDistrictResponse> getDropdownDistrict(AddressReq req) {
        // List<DropdownDistrictResponse> district = districtRepository.findByProvinceId(req.getProvinceId()).stream()
        //         .map(province1 -> DropdownDistrictResponse.builder().id(province1.getId())
        //                 .provinceId(province1.getProvinceId()).name(province1.getNameTh()).nameEn(province1.getNameEn())
        //                 .build())
        //         .toList();
        Map<String, List<String>> transformationRules = new HashMap<>();
                List<DropdownDistrictResponse> district = districtRepository.findByProvinceId(req.getProvinceId()).stream()
                .map(province1 -> playService.mapEntityToDto(province1, DropdownDistrictResponse.class, null))
                .toList();
        return district;
    }

    public List<DropdownSubDistrictResponse> getDropdownSubDistrict(AddressReq req) {
        List<DropdownSubDistrictResponse> subDistrict = subDistrictRepository.findByDistrictId(req.getDistrictId()).stream()
                .map(subDistrict1 -> DropdownSubDistrictResponse.builder().id(subDistrict1.getId())
                        .districtId(subDistrict1.getDistrictId()).zipCode(subDistrict1.getZipCode()).name(subDistrict1.getNameTh()).nameEn(subDistrict1.getNameEn())
                        .build())
                .toList();
        return subDistrict;
    }
}
