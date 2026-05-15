package com.stock.demo.infrastructure.exception;

public class CategoryPersistenceException extends RuntimeException {
    public CategoryPersistenceException(String message) {
        super(message);
    }
}
