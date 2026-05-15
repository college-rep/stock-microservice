package com.stock.demo.infrastructure.input.rest;

import com.stock.demo.application.dto.ArticleRequest;
import com.stock.demo.application.dto.ArticleResponse;
import com.stock.demo.application.handler.IArticleHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.stock.demo.util.ArticleConstants.*;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleRestController {
    private final IArticleHandler articleHandler;

    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> createArticle(@RequestBody ArticleRequest articleRequest) {
        articleHandler.createArticle(articleRequest);
        RestResponse response=new RestResponse(ARTICLE_CREATED, articleRequest);
        return new ResponseEntity<>(response.getResponse(), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<ArticleResponse> getArticleById(
            @RequestParam(defaultValue="0") Long id) {
        return ResponseEntity.ok(articleHandler.getArticleResponseById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ArticleResponse>> getArticles(
            @RequestParam(defaultValue="true") boolean ascendingOrder,
            @RequestParam(defaultValue = "article") String comparator) {
        List<ArticleResponse> articles = articleHandler.getArticles(ascendingOrder,comparator);
//        Pageable pageable = PageRequest.of(page, size);
        Pageable pageable = PageRequest.of(0, articles.size());
        return ResponseEntity.ok(new PageImpl<>(articles, pageable, articles.size()));
    }

    @PutMapping("/")
    public ResponseEntity<Map<String, Object>> updateArticle(@RequestBody ArticleRequest articleRequest) {
        articleHandler.updateArticle(articleRequest);
        RestResponse response=new RestResponse(ARTICLE_UPDATED, articleRequest);
        return new ResponseEntity<>(response.getResponse(), HttpStatus.CREATED);
    }

//    @GetMapping("/page")
//    public ResponseEntity<Page<ArticleResponse>> getArticles(
//            @RequestParam(defaultValue = "0") int page,//page you want to get
//            @RequestParam(defaultValue = "10") Long pageSize,
//            @RequestParam(defaultValue = "true") Boolean ascendingOrder,
//            @RequestParam(defaultValue = "article") String comparator) {
//        PageResponse<ArticleResponse> articleResponses =
//                 articleHandler.getArticleResponses(ascendingOrder,comparator,pageSize, (long)page);
//        Pageable pageable = PageRequest.of(page, Math.toIntExact(pageSize));
//        return ResponseEntity.ok(new PageImpl<>
//                (articleResponses.getContent()
//                        ,pageable, articleResponses.getTotalElements()));
//    }
}
