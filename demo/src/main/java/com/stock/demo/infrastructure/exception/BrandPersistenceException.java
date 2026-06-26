package com.stock.demo.infrastructure.exception;

public class BrandPersistenceException extends RuntimeException {
    public BrandPersistenceException(String message) {
        super(message);
    }
}
