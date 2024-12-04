package com.example.demo.dto.Response;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.example.demo.util.Constant.ApiReturn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
public class BaseResponse {
    private BaseStatusResponse status;
    
    private Object content;

    private Page page;

    public BaseResponse() {
        BaseStatusResponse status = new BaseStatusResponse();
        status.setCode(ApiReturn.SUCCESS.code());
        status.setDescription(ApiReturn.SUCCESS.description());
        this.status = status;
    }


    @SuppressWarnings({  "rawtypes" })
    public BaseResponse(Object content) {
        if (content instanceof PageImpl){
            this.page = ((Page) content);
        }else{
            this.content = content;
        }
        BaseStatusResponse status = new BaseStatusResponse();
        status.setCode(ApiReturn.SUCCESS.code());
        status.setDescription(ApiReturn.SUCCESS.description());
        
        this.status = status;
    }
    
}
