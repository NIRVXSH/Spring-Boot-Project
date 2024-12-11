package com.example.demo.service.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Request.StpProductReq;
import com.example.demo.dto.Response.BaseDetailsResponse;
import com.example.demo.dto.Response.BaseResponse;
import com.example.demo.dto.Response.BaseStatusResponse;
import com.example.demo.entity.StpProduct;
import com.example.demo.repository.StpProductRepository;
import com.example.demo.service.ProductService;
import com.example.demo.util.Constant.ApiReturn;
import com.example.demo.util.Constant.StatusFlag;

import jakarta.transaction.Transactional;
@Service
public class ProductServiceImpl implements ProductService {

   @Autowired
    private StpProductRepository stpProductRepository;

    public BaseResponse validateCreateProduct(StpProductReq req) {
        String code = null;
        String description = null;
        List<BaseDetailsResponse> detail = new ArrayList<>();
        BaseStatusResponse responseStatus = null;
        BaseResponse response = null;

        List<StpProduct> check = stpProductRepository.findByNameOrNameEnAndCreateBy(req.getName(), req.getNameEn() ,req.getCreatedBy());
        List<StpProduct> validate=stpProductRepository.findByNameOrNameEnAndNotId(req.getName(), req.getNameEn(), req.getId());
        if (!check.isEmpty() || !validate.isEmpty()) {
            code = ApiReturn.BAD_REQUEST.code();
            description = ApiReturn.BAD_REQUEST.description();
            detail.add(new BaseDetailsResponse("Product", "Already exists in system"));
        }
        
        if (code == null && description == null && detail.isEmpty()) {
            code = ApiReturn.SUCCESS.code();
            description = ApiReturn.SUCCESS.description();
            detail.add(new BaseDetailsResponse("Product", "Success"));
        }
        responseStatus = new BaseStatusResponse(code, description, detail);
        response = new BaseResponse(responseStatus);
        return response;
    }

    @Override
    public BaseResponse createProduct(StpProductReq req) {
        BaseResponse response = validateCreateProduct(req);
        BaseStatusResponse validate = (BaseStatusResponse) response.getContent();
        if (validate.getCode().equals(ApiReturn.SUCCESS.code())) {
            stpProductRepository.save(StpProduct.builder()
                    .id(UUID.randomUUID().toString())
                    .available(req.getAvailable())
                    .categoryCode(req.getCategoryCode())
                    .createdBy(req.getCreatedBy())
                    .createdDate(LocalDateTime.now())
                    .name(req.getName())
                    .nameEn(req.getNameEn())
                    .price(req.getPrice())
                    .statusFlag(StatusFlag.ACTIVE.code())
                    .build());
        } else {
            List<BaseDetailsResponse> details = new ArrayList<>();
            details.add(new BaseDetailsResponse("Product", "already use in this web"));
            return new BaseResponse(new BaseStatusResponse(ApiReturn.BAD_REQUEST.code(), "error", details));
        }
        return response;
    }

    @Override
    @Transactional
    public BaseResponse deleteProduct(StpProductReq req) {
        String code = ApiReturn.SUCCESS.code();
        String description = ApiReturn.SUCCESS.description();
        List<BaseDetailsResponse> detail = new ArrayList<>();
        Optional<StpProduct> check= stpProductRepository.findById(req.getId());
        if(!check.isEmpty()){
            check.get().setStatusFlag(StatusFlag.DELETE.code());
            check.get().setUpdatedBy(req.getActionId());
            check.get().setUpdatedDate(LocalDateTime.now());
            stpProductRepository.save(check.get());
            detail.add(new BaseDetailsResponse("Product", ApiReturn.SUCCESS.description()));
        }else {
            code = ApiReturn.BAD_REQUEST.code();
            description = ApiReturn.BAD_REQUEST.description();
            detail.add(new BaseDetailsResponse("Product", ApiReturn.NOT_FOUND.description()));
        }
        BaseStatusResponse responseStatus = new BaseStatusResponse(code, description, detail);
        BaseResponse response = new BaseResponse(responseStatus);
        return response;
    }


    @Override
    @Transactional
    public BaseResponse manageProduct(StpProductReq req) {
        StpProduct check= stpProductRepository.findById(req.getId()).orElse(null);
        if(check==null){
            return new BaseResponse(new BaseStatusResponse(ApiReturn.BAD_REQUEST.code(), "error", List.of(new BaseDetailsResponse("Product", ApiReturn.NOT_FOUND.description()))));
        }
        BaseResponse response = validateCreateProduct(req);
        BaseStatusResponse validate = (BaseStatusResponse) response.getContent();
        if(validate.getCode().equals(ApiReturn.SUCCESS.code())){
            check.setUpdatedBy(req.getActionId());
            check.setUpdatedDate(LocalDateTime.now());
            if(req.getAvailable()!=null) check.setAvailable(req.getAvailable());
            if(req.getImageProduct()!=null) check.setImageProduct(req.getImageProduct());
            if(req.getDiscountCode()!=null) check.setDiscountCode(req.getDiscountCode());
            if(req.getPrice()!=null) check.setPrice(req.getPrice());
            if(req.getName()!=null) check.setName(req.getName());
            if(req.getNameEn()!=null) check.setNameEn(req.getNameEn());
            if(req.getStatusFlag()!=null) check.setStatusFlag(req.getStatusFlag());
        } else {
            List<BaseDetailsResponse> details = new ArrayList<>();
            details.add(new BaseDetailsResponse("Product", "Name Product already use in this web"));
            response = new BaseResponse(new BaseStatusResponse(ApiReturn.BAD_REQUEST.code(), "error", details));
        }     
        return response;
    }


}
