package com.stock.demo.domain.exception;

import java.util.List;

public class ArticleUseCaseException extends RuntimeException {
  private final List<String> errorList;
  public ArticleUseCaseException(List<String> errorList) {
    super("ARTICLE_VALIDATION_ERROR");
    this.errorList = errorList;
  }
  public List<String> getErrorList() {
    return errorList;
  }
}
