package com.example.demo.service.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
import com.example.demo.utils.Constant.ApiReturn;

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

        List<StpProduct> check = stpProductRepository.findByNameOrNameEn(req.getName(), req.getNameEn());
        if (!check.isEmpty()) {
            code = ApiReturn.BAD_REQUEST.code();
            description = ApiReturn.BAD_REQUEST.description();
            detail.add(new BaseDetailsResponse("Product", "Already exists in system"));
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
    public BaseResponse createProduct(StpProductReq req) {
        BaseResponse response = validateCreateProduct(req);
        BaseStatusResponse validate = (BaseStatusResponse) response.getContent();
        if (validate.getCode().equals(ApiReturn.SUCCESS.code())) {
            stpProductRepository.save(StpProduct.builder()
                    .id(UUID.randomUUID().toString())
                    .available(req.getAvailable())
                    .categoryCode(req.getCategoryCode())
                    .createdBy(req.getCreatedBy())
                    .createdDate(LocalDate.now())
                    .name(req.getName())
                    .nameEn(req.getNameEn())
                    .discountCode(req.getDiscountCode())
                    .price(req.getPrice())
                    .statusFlag("ACTIVE")
                    .build());
        } else {
            List<BaseDetailsResponse> details = new ArrayList<>();
            details.add(new BaseDetailsResponse("Product", "already use in this web"));
            return new BaseResponse(new BaseStatusResponse(ApiReturn.BAD_REQUEST.code(), "error", details));
        }
        // TODO Auto-generated method stub
        return response;
    }

}
