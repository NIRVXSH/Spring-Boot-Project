package com.example.demo.dto.Response;

import java.util.List;

import com.example.demo.util.Constant.ApiReturn;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)
public class BaseStatusResponse {
    private String code;
    private String description;
    private List<BaseDetailsResponse> details;

    public BaseStatusResponse(List<BaseDetailsResponse> details) {
        String code = ApiReturn.SUCCESS.code();
        String description = ApiReturn.SUCCESS.description();
        this.code = code;
        this.description = description;
        this.details = details;
    }

    public BaseStatusResponse(String statusCode , String message) {
        String code = ApiReturn.SUCCESS.code();
        String description = ApiReturn.SUCCESS.description();

        if (statusCode.equalsIgnoreCase(ApiReturn.BAD_REQUEST.code())) {
            code = ApiReturn.BAD_REQUEST.code();
            description = ApiReturn.BAD_REQUEST.description();
        }
        this.code = code;
        this.description = description;
    }

}
