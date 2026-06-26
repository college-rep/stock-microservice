package com.stock.demo.infrastructure.input.rest;

import com.stock.demo.application.dto.ArticleSaleRequest;
import com.stock.demo.application.dto.ArticleSaleResponse;
import com.stock.demo.application.handler.IArticleSaleHandler;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;

import static com.stock.demo.util.ArticleSaleConstants.*;

@RestController
@RequestMapping("/article_sale")
@RequiredArgsConstructor
public class ArticleSaleRestController {
    private final IArticleSaleHandler articleSaleHandler;

    @GetMapping("/test")
    public ResponseEntity<Map<String,Object>> responseTest(){
        Map<String,Object> map=new HashMap<>();
        map.put("message","this is a test of the stock microservice");
        return ResponseEntity.ok(map);
    }
    @GetMapping("/")
    public ResponseEntity<ArticleSaleResponse> getArticleSaleById(
            @RequestParam(name="id") Long id) {
        return ResponseEntity.ok(articleSaleHandler.getArticleSaleResponseById(id));
    }
    @GetMapping("/all")
    public ResponseEntity<Page<ArticleSaleResponse>> getCategories(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "2025-06-26T10:15:30.123")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime start,
            @RequestParam(defaultValue="2026-06-26T10:15:30.123")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime end) {
        List<ArticleSaleResponse> articleSaleResponses = articleSaleHandler.getArticleSaleResponses(start,end);
//        Pageable pageable = PageRequest.of(page, size);
        Pageable pageable = PageRequest.of(0, articleSaleResponses.size());
        return ResponseEntity.ok(new PageImpl<>(articleSaleResponses, pageable, articleSaleResponses.size()));
    }
    @PostMapping("/")
    public ResponseEntity<Map<String,Object>> createArticleSale(@RequestBody ArticleSaleRequest articleSaleRequest) {
        ArticleSaleResponse articleSaleResponse=articleSaleHandler.createArticleSale(articleSaleRequest);
        RestResponse response= new RestResponse(ARTICLE_SALE_CREATED,
                articleSaleResponse);
        return new ResponseEntity<>(response.getResponse(),
                HttpStatus.CREATED);
    }
    @DeleteMapping("/")
    public ResponseEntity<Map<String,Object>> deleteArticleSale(
            @RequestParam(defaultValue = "0") Long id
    ) {
        articleSaleHandler.deleteArticleSaleById(id);
        RestResponse response= new RestResponse(ARTICLE_SALE_DELETED,
                id);
        return new ResponseEntity<>(response.getResponse(),
                HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<Map<String,Object>> deleteArticleSale(
            @RequestParam(defaultValue = "2025-06-26T10:15:30.123")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime start,
            @RequestParam(defaultValue="2026-06-26T10:15:30.123")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime end
    ) {
        articleSaleHandler.deleteArticleSalesByDate(start,end);
        RestResponse response= new RestResponse(ARTICLE_SALES_DELETED,
                "many deletions");
        return new ResponseEntity<>(response.getResponse(),
                HttpStatus.OK);
    }
}
