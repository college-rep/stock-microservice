package com.stock.demo.infrastructure.input.rest;

import com.stock.demo.application.dto.CategoryRequest;
import com.stock.demo.application.dto.CategoryResponse;
import com.stock.demo.application.handler.ICategoryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.stock.demo.util.CategoryConstants.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class    CategoryRestController {
    //every independence we inject are interfaces
    private final ICategoryHandler categoryHandler;
    private final Long userId=5L;
    //logging
    private static final Logger logger = LoggerFactory.getLogger(ICategoryHandler.class);
    @PostMapping("/")
    //we'll return a response entity of a  Void type because we're not interested
    //showing the user/client anything beyond the creation being made
    public ResponseEntity<Map<String,Object>> createCategory(@RequestBody CategoryRequest categoryRequest) {
        CategoryResponse categoryResponse=categoryHandler.createCategory(categoryRequest);
        //System.out.println("test for rest controller");
        //System.out.println(categoryResponse.getId());
        //CategoryResponse categoryResponse=categoryHandler.getCategoryResponseByName(categoryRequest.getName());
        RestResponse response= new RestResponse(CATEGORY_CREATED,
                categoryResponse);
        logger.atInfo()
                .setMessage("a category was created")
                .addKeyValue("userId", userId)
                .addKeyValue("categoryId", categoryResponse.getId())
                .addKeyValue("categoryName", categoryResponse.getName())
                .addKeyValue("categoryDescription", categoryResponse.getDescription())
                .log();
        return new ResponseEntity<>(response.getResponse(),
        HttpStatus.CREATED);
    }
    @GetMapping("/test")
    public ResponseEntity<Map<String,Object>> responseTest(){
        Map<String,Object> map=new HashMap<>();
        map.put("message","this is a test of the stock microservice");
        return ResponseEntity.ok(map);
    }
    @GetMapping("/")
    public ResponseEntity<CategoryResponse> getCategoryById(
            @RequestParam(name="id") Long id) {
        return ResponseEntity.ok(categoryHandler.getCategoryResponseById(id));
    }

    @GetMapping("/name")
    public ResponseEntity<CategoryResponse> getCategoryByName(
            @RequestParam(defaultValue="") String name) {
        return ResponseEntity.ok(categoryHandler.getCategoryResponseByName(name));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<CategoryResponse>> getCategories(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue="true") boolean ascendingOrder) {
        List<CategoryResponse> categoryResponses = categoryHandler.getCategoryResponses(ascendingOrder);
//        Pageable pageable = PageRequest.of(page, size);
        Pageable pageable = PageRequest.of(0, categoryResponses.size());
        return ResponseEntity.ok(new PageImpl<>(categoryResponses, pageable, categoryResponses.size()));
    }

    @PutMapping("/")
    public ResponseEntity<Map<String,Object>> updateCategory(
            @RequestBody CategoryRequest categoryRequest) {
        CategoryResponse categoryResponse=categoryHandler.updateCategory(categoryRequest);
        RestResponse response= new RestResponse(CATEGORY_UPDATED,
                categoryResponse);
        logger.atInfo()
                .setMessage("a category was modified")
                .addKeyValue("categoryId", categoryResponse.getId())
                .addKeyValue("old category name", categoryRequest.getName())
                .addKeyValue("old category description", categoryRequest.getDescription())
                .addKeyValue("new category name", categoryResponse.getName())
                .addKeyValue("new category description", categoryResponse.getDescription())
                .log();
        return new ResponseEntity<>(response.getResponse(),
                HttpStatus.OK);
    }
    @DeleteMapping("/")
    public ResponseEntity<Map<String,Object>> deleteCategory(
            @RequestParam(defaultValue = "0") Long id
    ) {
        categoryHandler.deleteCategoryById(id);
        RestResponse response= new RestResponse(CATEGORY_DELETED,
                id);
        return new ResponseEntity<>(response.getResponse(),
                HttpStatus.OK);
    }
}
