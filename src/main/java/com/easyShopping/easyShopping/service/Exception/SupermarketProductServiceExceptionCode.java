package com.easyShopping.easyShopping.service.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SupermarketProductServiceExceptionCode {
    SUPERMARKET_PRODUCT_NOT_FOUND("Supermarket product not found");

    private String message;
}
