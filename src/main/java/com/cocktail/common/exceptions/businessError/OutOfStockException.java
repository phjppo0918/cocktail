package com.cocktail.common.exceptions.businessError;

public class OutOfStockException extends RuntimeException {

    public OutOfStockException() {
        super("재고가 부족합니다");
    }

    public OutOfStockException(String message) {
        super(message);
    }
    public OutOfStockException(Double requestCount, Double remainCount) {
        super("재고가 부족합니다 : [요청 개수 : " +
                requestCount+", 잔여 개수 :"+ remainCount+" ]");
    }
}
