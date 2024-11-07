package com.example.demo.service.serviceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Request.StpUserReq;
import com.example.demo.dto.Response.BaseResponse;
import com.example.demo.entity.StpUser;
import com.example.demo.repository.StpUserRepository;
import com.example.demo.service.AuthenService;

import jakarta.transaction.Transactional;



@Service
public class AuthenServiceImpl implements AuthenService {

    @Autowired
    private StpUserRepository stpUserRepository;


    public BaseResponse validateUserCreate(StpUserReq req){
        BaseResponse response=new BaseResponse();
        return response;
    }

    @Override
    @Transactional
    public BaseResponse createUser(StpUserReq req) {
        BaseResponse response=new BaseResponse();
        List<StpUser> checkDup= stpUserRepository.findByUsername(req.getUsername());

        if(checkDup.isEmpty()){
            stpUserRepository.save(StpUser.builder().id(UUID.randomUUID().toString())
            .name(req.getName())
            .nameEn(req.getNameEn())
            .nickName(req.getNickName())
            .nickNameEn(req.getNickNameEn())
            .surname(req.getSurname())
            .surnameEn(req.getSurnameEn())
            .dateOfBirth(req.getDateOfBirth())
            .username(req.getUsername())
            .password(req.getPassword())
            .bankAccountNo(req.getBankAccountNo())
            .bankCode(req.getBankCode())
            .build());
            response.setContent("200");;
            
        }else{
            response.setContent("400");
        }
        
        return response;
    }
    
}
