package com.example.demo.cache.CacheLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.cache.CacheService;

@Component
public class CacheLoader implements CommandLineRunner {
    @Autowired
    private CacheService cacheService;

    public void run(String... args) {
        cacheService.initDistrictCache();
        cacheService.initProvinceCache();
        cacheService.initSubDistrictCache();
    }
}
