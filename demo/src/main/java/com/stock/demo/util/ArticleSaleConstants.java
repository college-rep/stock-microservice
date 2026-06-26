package com.stock.demo.util;

public class ArticleSaleConstants {
    private ArticleSaleConstants() {
        throw new IllegalStateException("Utility class: this class cannot be instantiated");
    }
    public static final String ARTICLE_SALE_CREATED = "The article sale was successfully created";
    public static final String ARTICLE_SALE_DELETED = "The article sale was successfully deleted";
    public static final String ARTICLE_SALES_DELETED = "The article sales were successfully deleted";
    public static final String ARTICLE_SALE_NOT_FOUND = "The article sale does not exist";
    public static final String ARTICLE_SALES_DO_NOT_EXIST = "there are no article sales in the database";
    public static final String ARTICLE_SALE_ID_NOT_FOUND = "The article sale id does not exist";
    public static final String ARTICLE_ID_NOT_PROVIDED = "The article id was not provided";
    public static final String ARTICLE_ID_NOT_FOUND = "The article id does not exist";
    public static final String ARTICLE_AMOUNT_NOT_PROVIDED = "The article amount was not provided";
    public static final String ARTICLE_AMOUNT_NOT_POSITIVE = "The article amount must be positive";
    public static final String ARTICLE_PRICE_NOT_PROVIDED = "The article price was not provided";
    public static final String ARTICLE_PRICE_NOT_POSITIVE = "The article price must be positive";
}
