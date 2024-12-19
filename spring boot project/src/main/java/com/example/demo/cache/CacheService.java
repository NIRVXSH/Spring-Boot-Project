package com.example.demo.cache;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Response.DropdownDistrictResponse;
import com.example.demo.dto.Response.DropdownProvinceResponse;
import com.example.demo.dto.Response.DropdownSubDistrictResponse;
import com.example.demo.repository.MstBankRepository;
import com.example.demo.repository.MstDistrictRepository;
import com.example.demo.repository.MstProvinceRepository;
import com.example.demo.repository.MstSubDistrictRepository;
import com.example.demo.util.UtilService;

@Service
public class CacheService {
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

    private ConcurrentHashMap<String, DropdownProvinceResponse> provinceCache = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, DropdownDistrictResponse> districtCache = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, DropdownSubDistrictResponse> subDistrictCache = new ConcurrentHashMap<>();

    public void initProvinceCache() {
        System.out.println("Init Province Cache");
        List<DropdownProvinceResponse> dtoList = provinceRepository.findAll().stream()
                .map(province1 -> utilService.mapEntityToDto(province1, DropdownProvinceResponse.class, null)).toList();
        dtoList.forEach(dto -> provinceCache.put(dto.getId(), dto));
    }

    public void initDistrictCache() {
        System.out.println("Init District Cache");
        List<DropdownDistrictResponse> dtoList = districtRepository.findAll().stream()
                .map(district1 -> utilService.mapEntityToDto(district1, DropdownDistrictResponse.class, null)).toList();
        dtoList.forEach(dto -> districtCache.put(dto.getId(), dto));
    }

    public void initSubDistrictCache() {
        System.out.println("Init SubDistrict Cache");
        List<DropdownSubDistrictResponse> dtoList = subDistrictRepository.findAll().stream()
                .map(subDistrict1 -> utilService.mapEntityToDto(subDistrict1, DropdownSubDistrictResponse.class, null)).toList();
        dtoList.forEach(dto -> subDistrictCache.put(dto.getId(), dto));
    }

    public List<DropdownProvinceResponse> getAllProvince() {
        return provinceCache.values().stream().toList();
    }

    public void refreshCache() {
        provinceCache.clear();
        districtCache.clear();
        subDistrictCache.clear();
        initProvinceCache();
        initDistrictCache();
        initSubDistrictCache();
        
    }

    public List <DropdownDistrictResponse> getDistrictByProvinceId(String provinceId){
        return districtCache.values().stream().filter(district1 -> district1.getProvinceId().equals(provinceId)).toList(); 
    }

    public List <DropdownSubDistrictResponse> getSubDistrictByDistrictId(String districtId){
        return subDistrictCache.values().stream().filter(subDistrict1 -> subDistrict1.getDistrictId().equals(districtId)).toList(); 
    }

}
