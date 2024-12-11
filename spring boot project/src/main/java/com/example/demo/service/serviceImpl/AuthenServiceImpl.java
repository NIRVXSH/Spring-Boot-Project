package com.example.demo.service.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Request.StpUserReq;
import com.example.demo.dto.Response.BaseDetailsResponse;
import com.example.demo.dto.Response.BaseResponse;
import com.example.demo.dto.Response.BaseStatusResponse;
import com.example.demo.entity.StpUser;
import com.example.demo.repository.StpUserRepository;
import com.example.demo.service.AuthenService;
import com.example.demo.util.Constant.ApiReturn;
import com.example.demo.util.Constant.StatusFlag;
import jakarta.transaction.Transactional;




@Service
public class AuthenServiceImpl implements AuthenService {

    @Autowired
    private StpUserRepository stpUserRepository;


    @Autowired
    PasswordEncoder encoder;

    public BaseResponse validateUserCreate(StpUserReq req){
        String code = null;
        String description = null;
        List<BaseDetailsResponse> detail = new ArrayList<>();
        BaseStatusResponse responseStatus = null;
        BaseResponse response = null;
        List<StpUser> check =stpUserRepository.findUserByUsername(req.getUsername());
        if(!check.isEmpty()){
            code = ApiReturn.BAD_REQUEST.code();
            description = ApiReturn.BAD_REQUEST.description();
            detail.add(new BaseDetailsResponse("User", "Already exists in system"));
        }
        if (code == null && description == null && detail.isEmpty()) {
            code = ApiReturn.SUCCESS.code();
            description = ApiReturn.SUCCESS.description();
            detail.add(new BaseDetailsResponse("field", "Success"));
        }
        responseStatus = new BaseStatusResponse(code, description, detail);
        response = new BaseResponse(responseStatus);
        return response;
    }

    @Override
    @Transactional
    public BaseResponse createUser(StpUserReq req) {
        BaseResponse response=validateUserCreate(req);
        BaseStatusResponse validate = (BaseStatusResponse) response.getContent();
        if(validate.getCode().equals(ApiReturn.SUCCESS.code())){
            stpUserRepository.save(StpUser.builder().id(UUID.randomUUID().toString())
            .name(req.getName())
            .nameEn(req.getNameEn())
            .nickName(req.getNickName())
            .nickNameEn(req.getNickNameEn())
            .surname(req.getSurname())
            .surnameEn(req.getSurnameEn())
            .dateOfBirth(req.getDateOfBirth())
            .username(req.getUsername())
            .password(encoder.encode(req.getPassword()))
            .roleId(req.getRoleId())
            .createDate(LocalDateTime.now())
            .statusFlag(StatusFlag.ACTIVE.code())
            .build());

            
        }else {
            List<BaseDetailsResponse> details = new ArrayList<>();
            details.add(new BaseDetailsResponse("User", "already use in this web"));
            return new BaseResponse(new BaseStatusResponse(ApiReturn.BAD_REQUEST.code(), "error", details));
        }
        return response;
    }

    @Override
    public BaseResponse changePassword(StpUserReq req) {
        BaseResponse response=new BaseResponse();
        StpUser validate=stpUserRepository.findById(req.getId()).orElse(null);
        if(validate!=null){
            response.setContent("200");
        }else{
            response.setContent("400");
        }
        return response;
    }


    public BaseResponse resetPassword(StpUserReq req){
        BaseResponse response=new BaseResponse();
        StpUser validate=stpUserRepository.findById(req.getId()).orElse(null);
        if(validate!=null){
            validate.setPassword(encoder.encode(req.getPassword()));
            stpUserRepository.save(validate);
            response.setContent("200");
        }else{
            response.setContent("400");
        }
        return response;
    }
    
}
