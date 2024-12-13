package com.example.demo.dto.Response;

import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayResponse {
    
    private String id;

    private String name;
  
    private String description;

    private Integer price;

    private HashMap<String, String> json;
}
