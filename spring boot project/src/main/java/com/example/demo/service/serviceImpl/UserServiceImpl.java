package com.example.demo.service.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.Request.BaseRequest;
import com.example.demo.dto.Request.StpPaymentReq;
import com.example.demo.dto.Response.BaseDetailsResponse;
import com.example.demo.dto.Response.BaseResponse;
import com.example.demo.dto.Response.BaseStatusResponse;
import com.example.demo.dto.Response.DropdownPaymentResponse;
import com.example.demo.entity.StpPayment;
import com.example.demo.repository.StpPaymentRepository;
import com.example.demo.service.UserService;
import com.example.demo.util.Constant.ApiReturn;
import com.example.demo.util.Constant.StatusFlag;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private StpPaymentRepository stpPaymentRepository;

    @Autowired
    private ModelMapper modelMapper;

    public BaseResponse validateCreateProduct(StpPaymentReq req) {
        String code = null;
        String description = null;
        List<BaseDetailsResponse> detail = new ArrayList<>();
        BaseStatusResponse responseStatus = null;
        BaseResponse response = null;

        List<StpPayment> check = stpPaymentRepository.findByCreatedByAndBankCodeAndBankAccountNo(req.getCreatedBy(),
                req.getBankCode(), req.getBankAccountNo());
        if (!check.isEmpty()) {
            code = ApiReturn.BAD_REQUEST.code();
            description = ApiReturn.BAD_REQUEST.description();
            detail.add(new BaseDetailsResponse("Payment", "Already exists in system"));
        }
        if (code == null && description == null && detail.isEmpty()) {
            code = ApiReturn.SUCCESS.code();
            description = ApiReturn.SUCCESS.description();
            detail.add(new BaseDetailsResponse("Payment", "Success"));
        }
        responseStatus = new BaseStatusResponse(code, description, detail);
        response = new BaseResponse(responseStatus);
        return response;
    }

    @Override
    public BaseResponse createPayment(StpPaymentReq req) {
        BaseResponse response = validateCreateProduct(req);
        BaseStatusResponse validate = (BaseStatusResponse) response.getContent();
        if (validate.getCode().equals(ApiReturn.SUCCESS.code())) {
            stpPaymentRepository.save(StpPayment.builder()
                    .id(UUID.randomUUID().toString())
                    .bankAccountNo(req.getBankAccountNo())
                    .paymentMethodCode(req.getPaymentMethodCode())
                    .bankCode(req.getBankCode())
                    .balance(req.getBalance() != null ? req.getBalance() : null)
                    .createdBy(req.getActionId())
                    .createdDate(LocalDateTime.now())
                    .statusFlag(StatusFlag.ACTIVE.code())
                    .createdBy(req.getActionId())
                    .createdDate(LocalDateTime.now())
                    .build());
        } else {
            List<BaseDetailsResponse> details = new ArrayList<>();
            details.add(new BaseDetailsResponse("Payment", "already use in this web"));
            return new BaseResponse(new BaseStatusResponse(ApiReturn.BAD_REQUEST.code(), "error", details));
        }
        return response;
    }

    @Override
    @Transactional
    public BaseResponse deletePayment(StpPaymentReq req) {
        String code = ApiReturn.SUCCESS.code();
        String description = ApiReturn.SUCCESS.description();
        List<BaseDetailsResponse> detail = new ArrayList<>();
        Optional<StpPayment> check= stpPaymentRepository.findById(req.getId());
        if(!check.isEmpty()){
            check.get().setStatusFlag(StatusFlag.DELETE.code());
            check.get().setUpdatedBy(req.getActionId());
            check.get().setUpdatedDate(LocalDateTime.now());
            stpPaymentRepository.save(check.get());
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
    @Transactional(readOnly = true)
    public List<DropdownPaymentResponse> getDropdownPayment(BaseRequest req) {

        List<StpPayment> data = stpPaymentRepository.findByCreatedBy(req.getActionId());
        List<DropdownPaymentResponse> response = new ArrayList<>();
        if (data != null) {
            response = data.stream().map(payment -> modelMapper.map(payment, DropdownPaymentResponse.class))
                    .collect(Collectors.toList());
        }
        return response;

    }

}
