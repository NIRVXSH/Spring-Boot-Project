package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Response.PlayResponse;
import com.example.demo.entity.Play;
import com.example.demo.service.PlayService;


@RestController
@RequestMapping("/play")
public class PlayController {

    @Autowired
    private PlayService playService;
    @PostMapping("/get-play-data")
    public List<PlayResponse> postMethodName(@RequestBody String entity) {
        
        return playService.getPlayList();
    }
    
}
