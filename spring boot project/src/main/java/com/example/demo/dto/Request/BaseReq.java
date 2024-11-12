package com.example.demo.dto.Request;

import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@Validated
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseReq {
    private String userId;
	private String actionId;
}
