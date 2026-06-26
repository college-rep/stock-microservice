package com.stock.demo.infrastructure.input.rest;

import com.stock.demo.application.handler.IArticleSaleHandler;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/article_sale")
@RequiredArgsConstructor
public class ArticleSaleRestController {
    private final IArticleSaleHandler categoryHandler;

    @GetMapping("/test")
    public ResponseEntity<Map<String,Object>> responseTest(){
        Map<String,Object> map=new HashMap<>();
        map.put("message","this is a test of the stock microservice");
        return ResponseEntity.ok(map);
    }
}
