
package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Request.AddressReq;
import com.example.demo.dto.Request.BaseRequest;
import com.example.demo.dto.Response.DropdownDistrictResponse;
import com.example.demo.dto.Response.DropdownPaymentResponse;
import com.example.demo.dto.Response.DropdownProvinceResponse;
import com.example.demo.dto.Response.DropdownResponse;
import com.example.demo.dto.Response.DropdownSubDistrictResponse;
import com.example.demo.service.MstService;

@RestController
@RequestMapping("/np/mst")
public class MstController {
    @Autowired
    private MstService mstService;


    @PostMapping("/getDropdownBank")
    public ResponseEntity<?> getDropdownPayment(@RequestBody BaseRequest req) throws IOException {
        try {
            List<DropdownResponse> response = mstService.getDropdownBank();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/getDropdownProvince")
    public ResponseEntity<?> getDropdownProvince(@RequestBody AddressReq req) throws IOException {
        try {
            List<DropdownProvinceResponse> response = mstService.getDropdownProvince();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/getDropdownDistrict")
    public ResponseEntity<?> getDropdownDistrict(@RequestBody AddressReq req) throws IOException {
        try {
            List<DropdownDistrictResponse> response = mstService.getDropdownDistrict(req);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/getDropdownSubDistrict")
    public ResponseEntity<?> getDropdownSubDistrict(@RequestBody AddressReq req) throws IOException {
        try {
            List<DropdownSubDistrictResponse> response = mstService.getDropdownSubDistrict(req);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
