package com.stock.demo.domain.exception;

import java.util.List;

public class ArticleSaleUseCaseException extends RuntimeException {
    private final List<String> errorList;
    public ArticleSaleUseCaseException(List<String> errorList) {
        super("ARTICLE_SALE_VALIDATION_ERROR");
        this.errorList = errorList;
    }
    public List<String> getErrorList() {
        return errorList;
    }
}
